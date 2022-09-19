package br.com.systemsgs.model;

import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
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

    @NotNull(message = "Certificado Obrigatório")
    @NotEmpty(message = "Certificado Obrigatório")
    private byte[] certificado;

    private String senhaCertificado;

    private String nsu;

    @Enumerated(EnumType.STRING)
    private AmbienteEnum ambiente;

    @JsonIgnore
    public byte[] getCertificado() {
        return certificado;
    }
}
