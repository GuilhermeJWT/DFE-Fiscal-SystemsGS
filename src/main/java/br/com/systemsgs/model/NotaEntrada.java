package br.com.systemsgs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "nota_entrada")
@SequenceGenerator(name = "NotaEntradaSeq", sequenceName = "SEQ_NOTA_ENTRADA", allocationSize = 1)
public class NotaEntrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NotaEntradaSeq")
    private Long id;
    private String schema;
    private byte[] xml;
    private String chave;
    private String nomeEmitente;
    private String cnpjEmitente;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
