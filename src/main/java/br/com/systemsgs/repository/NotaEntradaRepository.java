package br.com.systemsgs.repository;

import br.com.systemsgs.model.NotaEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Long> {

}
