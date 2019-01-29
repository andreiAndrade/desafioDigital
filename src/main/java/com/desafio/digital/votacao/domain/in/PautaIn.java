package com.desafio.digital.votacao.domain.in;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class PautaIn {

    @ApiModelProperty(notes = "Título da pauta", required = true)
    @NotBlank(message = "O título não pode ser nulo")
    private String titulo;

    @ApiModelProperty(notes = "Descrição da pauta", required = true)
    @NotBlank(message = "A descrição não pode ser nulo")
    private String descricao;

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
