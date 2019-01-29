package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.domain.Voto;
import com.desafio.digital.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/voto")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Object> criarVoto(@Valid @RequestBody Voto voto) throws Exception {
        Voto votoCriado = votoService.votar(voto);
        return new ResponseEntity<>(votoCriado, HttpStatus.CREATED);
    }
}
