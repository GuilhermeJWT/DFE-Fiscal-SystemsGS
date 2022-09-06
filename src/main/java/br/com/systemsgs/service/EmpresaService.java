package br.com.systemsgs.service;

import br.com.systemsgs.model.Empresa;
import br.com.systemsgs.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    private Empresa salvar(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    private void deletar(Long idEmpresa){
        empresaRepository.deleteById(idEmpresa);
    }

    private List<Empresa> listarTudo(){
        return empresaRepository.findAll();
    }

}
