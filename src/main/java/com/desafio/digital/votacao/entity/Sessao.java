package com.desafio.digital.votacao.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

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
