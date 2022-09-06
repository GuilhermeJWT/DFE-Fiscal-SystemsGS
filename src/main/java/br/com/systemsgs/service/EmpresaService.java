package br.com.systemsgs.service;

import br.com.systemsgs.model.Empresa;
import br.com.systemsgs.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    private Empresa salvar(Empresa empresa){
        return empresaRepository.save(empresa);
    }

}
