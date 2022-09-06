package br.com.systemsgs.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "empresa")
@SequenceGenerator(name = "EmpresaSeq", sequenceName = "SEQ_EMPRESA", allocationSize = 1)
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmpresaSeq")
    private Long id;
    @NotEmpty(message = "Cpf/Cnpj Obrigatório")
    private String cpfCnpj;
    private String razaoSocial;
    private byte[] certificado;
    private String senhaCertificado;
    private String nsu;

}
