package com.desafio.digital.votacao.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "O id da pauta não pode ser null")
    @Column(name = "id_pauta", nullable = false)
    private Long idPauta;

    @Column(name = "inicio_sessao", nullable = false)
    private LocalDateTime inicioSessao;

    @Column(name = "tempo_duracao_sessao", nullable = false)
    private Integer tempoDuracaoSessao;

    @Column(name = "total_votos")
    private Integer totalVotos;

    @Column(name = "votos_favor")
    private Long votosFavor;

    @Column(name = "votos_contra")
    private Long votosContra;

    public Sessao() {
        this.inicioSessao = LocalDateTime.now();
        this.tempoDuracaoSessao = 1;
    }

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
