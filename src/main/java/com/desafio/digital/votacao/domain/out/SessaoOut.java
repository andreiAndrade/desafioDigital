package com.desafio.digital.votacao.domain.out;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class SessaoOut {

    @ApiModelProperty(notes = "ID auto-gerado para identificação da entidade", allowEmptyValue = true)
    private Long id;

    @ApiModelProperty(notes = "O id da pauta da sessão", required = true)
    private Long idPauta;

    @ApiModelProperty(notes = "Data/hora do ínicio da sessão", required = true)
    private LocalDateTime inicioSessao;

    @ApiModelProperty(notes = "Tempo de duração da sessão", required = true)
    private Integer tempoDuracaoSessao;

    @ApiModelProperty(notes = "Total de votos")
    private Integer totalVotos;

    @ApiModelProperty(notes = "Votos a favor")
    private Long votosFavor;

    @ApiModelProperty(notes = "Votos contra")
    private Long votosContra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public LocalDateTime getInicioSessao() {
        return inicioSessao;
    }

    public void setInicioSessao(LocalDateTime inicioSessao) {
        this.inicioSessao = inicioSessao;
    }

    public Integer getTempoDuracaoSessao() {
        return tempoDuracaoSessao;
    }

    public void setTempoDuracaoSessao(Integer tempoDuracaoSessao) {
        this.tempoDuracaoSessao = tempoDuracaoSessao;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public Long getVotosFavor() {
        return votosFavor;
    }

    public void setVotosFavor(Long votosFavor) {
        this.votosFavor = votosFavor;
    }

    public Long getVotosContra() {
        return votosContra;
    }

    public void setVotosContra(Long votosContra) {
        this.votosContra = votosContra;
    }
}
