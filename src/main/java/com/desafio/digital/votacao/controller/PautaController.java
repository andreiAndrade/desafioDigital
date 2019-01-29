package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.domain.Pauta;
import com.desafio.digital.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody Pauta pauta) {
        Pauta pautaCriada = pautaService.criarPauta(pauta);
        return new ResponseEntity<>(pautaCriada, HttpStatus.CREATED);
    }
}
