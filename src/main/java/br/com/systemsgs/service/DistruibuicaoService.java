package br.com.systemsgs.service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.systemsgs.model.Empresa;
import org.springframework.stereotype.Service;

@Service
public class DistruibuicaoService {

    public void consultaNotas(Empresa empresa) throws CertificadoException, NfeException {

        ConfiguracoesNfe configuracao = criaConfiguracoes(empresa);
        String nsu = ObjetoUtil.verifica(empresa.getNsu()).orElse("000000000000000");

        RetDistDFeInt retorno = Nfe.distribuicaoDfe(configuracao, PessoaEnum.JURIDICA, empresa.getCpfCnpj(),
                ConsultaDFeEnum.NSU, nsu);

    }

    public ConfiguracoesNfe criaConfiguracoes(Empresa empresa) throws CertificadoException {
        Certificado certificado = CertificadoService.certificadoPfxBytes(empresa.getCertificado(), empresa.getSenhaCertificado());

        ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.valueOf(empresa.getUf()),
                empresa.getAmbiente(),
                certificado,
                "C:/Users/Guilherme Santos/nfe/schemas");

    }

}
