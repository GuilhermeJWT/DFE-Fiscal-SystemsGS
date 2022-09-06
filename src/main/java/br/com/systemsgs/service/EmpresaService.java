package br.com.systemsgs.service;

import br.com.systemsgs.exceptions.ResourceNotFoundException;
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
    public Empresa salvar(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public void deletar(Long idEmpresa){
        empresaRepository.deleteById(idEmpresa);
    }

    public List<Empresa> listarEmpresas(){
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id){
        return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não Encontrada: " + id));
    }

}
