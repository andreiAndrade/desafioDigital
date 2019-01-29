package com.desafio.digital.votacao.entity;

import com.desafio.digital.votacao.entity.enums.VotoEnum;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiIgnore
@Entity
public class Voto {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cpf_associado", nullable = false)
    private String cpfAssociado;

    @Column(name = "voto", nullable = false)
    private VotoEnum aceite;

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

    public VotoEnum getAceite() {
        return aceite;
    }

    public void setAceite(VotoEnum aceite) {
        this.aceite = aceite;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }
}
