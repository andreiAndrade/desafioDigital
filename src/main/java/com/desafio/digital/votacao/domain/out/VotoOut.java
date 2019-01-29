package com.desafio.digital.votacao.domain.out;

import com.desafio.digital.votacao.entity.enums.VotoEnum;
import io.swagger.annotations.ApiModelProperty;

public class VotoOut {

    @ApiModelProperty(notes = "ID auto-gerado para identificação da entidade", allowEmptyValue = true)
    private Long id;

    @ApiModelProperty(notes = "Cpf do associado que votou", required = true)
    private String cpfAssociado;

    @ApiModelProperty(notes = "O voto realizado pelo associado", required = true)
    private VotoEnum aceite;

    @ApiModelProperty(notes = "Id da pauta que o associado votou", required = true)
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
