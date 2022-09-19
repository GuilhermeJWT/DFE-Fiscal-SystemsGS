package br.com.systemsgs.service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.schema.resnfe.ResNFe;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.systemsgs.model.Empresa;
import br.com.systemsgs.model.NotaEntrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DistruibuicaoService {

    public void consultaNotas(Empresa empresa) throws Exception {

        ConfiguracoesNfe configuracao = criaConfiguracoes(empresa);
        String nsu = ObjetoUtil.verifica(empresa.getNsu()).orElse("000000000000000");

        List<String> listaNotasManifestar = new ArrayList<>();

        RetDistDFeInt retorno = Nfe.distribuicaoDfe(configuracao, PessoaEnum.JURIDICA, empresa.getCpfCnpj(),
                ConsultaDFeEnum.NSU, nsu);

        if(!retorno.getCStat().equals(StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO)){
            if(retorno.getCStat().equals(StatusEnum.CONSUMO_INDEVIDO)){

            }else{
                throw new Exception("Erro ao Pesquisar Notas:" + retorno.getCStat() + " - " + retorno.getXMotivo());
            }
        }

        for (RetDistDFeInt.LoteDistDFeInt.DocZip doc : retorno.getLoteDistDFeInt().getDocZip()) {
            String xml = XmlNfeUtil.gZipToXml(doc.getValue());
            log.info("Xml:" + xml);
            log.info("Schema:" + doc.getSchema());
            log.info("Nsu:" + doc.getNSU());

            switch (doc.getSchema()){
                case "resNFe_v1.01.xsd" :
                    ResNFe resNFe =  XmlNfeUtil.xmlToObject(xml, ResNFe.class);
                    String chave = resNFe.getChNFe();

                    listaNotasManifestar.add(chave);
                    break;

                case "procNFe_v4.00.xsd" :
                    TNfeProc nfe = XmlNfeUtil.xmlToObject(xml, TNfeProc.class);
                    NotaEntrada notaEntrada = new NotaEntrada();
                    notaEntrada.setChave(nfe.getNFe().getInfNFe().getId().substring(3));
                    notaEntrada.setEmpresa(empresa);
                    notaEntrada.setSchema(doc.getSchema());
                    notaEntrada.setCnpjEmitente(nfe.getNFe().getInfNFe().getEmit().getCNPJ());
                    notaEntrada.setNomeEmitente(nfe.getNFe().getInfNFe().getEmit().getXNome());
                    notaEntrada.setValor(new BigDecimal(nfe.getNFe().getInfNFe().getTotal().getICMSTot().getVNF()));
            }
        }

    }

    public ConfiguracoesNfe criaConfiguracoes(Empresa empresa) throws CertificadoException {
        Certificado certificado = CertificadoService.certificadoPfxBytes(empresa.getCertificado(), empresa.getSenhaCertificado());

        ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.valueOf(empresa.getUf()),
                empresa.getAmbiente(),
                certificado,
                "C:/Users/Guilherme Santos/nfe/schemas");
    }

}
