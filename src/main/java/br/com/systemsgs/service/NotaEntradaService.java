package br.com.systemsgs.service;

import br.com.systemsgs.model.NotaEntrada;
import br.com.systemsgs.repository.NotaEntradaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotaEntradaService {

    private final NotaEntradaRepository notaEntradaRepository;

    public NotaEntradaService(NotaEntradaRepository notaEntradaRepository) {
        this.notaEntradaRepository = notaEntradaRepository;
    }

    @Transactional
    private NotaEntrada salvar(NotaEntrada notaEntrada){
        return notaEntradaRepository.save(notaEntrada);
    }

    private void deletar(Long idNota){
        notaEntradaRepository.deleteById(idNota);
    }

    private List<NotaEntrada> listarTudo(){
        return notaEntradaRepository.findAll();
    }

}
