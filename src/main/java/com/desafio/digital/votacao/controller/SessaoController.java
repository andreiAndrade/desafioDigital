package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.domain.Sessao;
import com.desafio.digital.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/sessao")
public class SessaoController {
    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<Sessao> criarSessao(@Valid @RequestBody Sessao sessao) {
        Sessao sessaoCriada = sessaoService.criarSessao(sessao);
        return new ResponseEntity<>(sessaoCriada, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Sessao> finalizarSessao(@RequestParam(name = "sessao") Long idSessao) {
        Sessao sessaoFinalizada = sessaoService.finalizarSessao(idSessao);
        return new ResponseEntity<>(sessaoFinalizada, HttpStatus.OK);
    }
}
