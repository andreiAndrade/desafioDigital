package com.desafio.digital.votacao.domain;

import com.desafio.digital.votacao.domain.enums.VotoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Voto {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "O cpf do associado não pode ser nulo")
    @Column(name = "cpf_associado", nullable = false)
    private String cpfAssociado;

    @NotNull(message = "O voto não pode ser nulo")
    @Column(name = "voto", nullable = false)
    private VotoEnum voto;

    @NotNull(message = "O id da pauta não pode ser nulo")
    @Column(name = "id_pauta", nullable = false)
    private Long idPauta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public VotoEnum getVoto() {
        return voto;
    }

    public void setVoto(VotoEnum voto) {
        this.voto = voto;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }
}
