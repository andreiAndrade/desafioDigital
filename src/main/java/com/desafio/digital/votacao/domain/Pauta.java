package com.desafio.digital.votacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Pauta {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "O título não pode ser nulo")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição não pode ser nulo")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
