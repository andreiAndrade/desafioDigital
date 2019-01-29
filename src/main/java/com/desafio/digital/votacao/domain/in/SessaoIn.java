package com.desafio.digital.votacao.domain.in;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class SessaoIn {

    @ApiModelProperty(notes = "O id da pauta da sessão", required = true)
    @NotNull(message = "O id da pauta não pode ser null")
    private Long idPauta;

    @ApiModelProperty(notes = "Tempo de duração da sessão")
    private Integer tempoDuracaoSessao;

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getTempoDuracaoSessao() {
        return tempoDuracaoSessao;
    }

    public void setTempoDuracaoSessao(Integer tempoDuracaoSessao) {
        this.tempoDuracaoSessao = tempoDuracaoSessao;
    }
}
