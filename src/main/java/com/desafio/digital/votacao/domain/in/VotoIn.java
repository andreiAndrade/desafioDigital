package com.desafio.digital.votacao.domain.in;

import com.desafio.digital.votacao.entity.enums.VotoEnum;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VotoIn {

    @ApiModelProperty(notes = "Cpf do associado que votou", required = true)
    @NotBlank(message = "O cpf do associado não pode ser nulo")
    private String cpfAssociado;

    @ApiModelProperty(notes = "O aceite realizado pelo associado", required = true)
    @NotNull(message = "O aceite não pode ser nulo")
    private VotoEnum aceite;

    @ApiModelProperty(notes = "Id da pauta que o associado votou", required = true)
    @NotNull(message = "O id da pauta não pode ser nulo")
    private Long idPauta;

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
