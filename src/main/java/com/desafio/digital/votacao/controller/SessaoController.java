package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.config.ApiVersion;
import com.desafio.digital.votacao.domain.in.SessaoIn;
import com.desafio.digital.votacao.domain.out.SessaoOut;
import com.desafio.digital.votacao.entity.Sessao;
import com.desafio.digital.votacao.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "Serviço para Manipulação de Sessão")
@RestController
@RequestMapping(value = "/sessao")
public class SessaoController {
    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @ApiOperation(value = "Cria uma sessão", response = Sessao.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessão criada com sucesso"),
            @ApiResponse(code = 400, message = "Campos informados não atrendem as regras de negócio"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping(produces = ApiVersion.V1)
    public ResponseEntity<SessaoOut> criarSessao(
            @ApiParam(value = "Sessão que será salva no banco de dados", required = true) @Valid @RequestBody SessaoIn sessaoIn) {
        SessaoOut sessaoCriada = sessaoService.criarSessao(sessaoIn);
        return new ResponseEntity<>(sessaoCriada, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Finaliza uma sessão e contabiliza os votos", response = Sessao.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sessão finalizada com sucesso"),
            @ApiResponse(code = 400, message = "Campos informados não atrendem as regras de negócio"),
            @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 404, message = "Sessão não encontrada")
    })
    @PutMapping(produces = ApiVersion.V1)
    public ResponseEntity<SessaoOut> finalizarSessao(
            @ApiParam(value = "Id da sessão que será encerrada", required = true)
            @RequestParam(name = "sessao") Long idSessao) {
        SessaoOut sessaoFinalizada = sessaoService.finalizarSessao(idSessao);
        return new ResponseEntity<>(sessaoFinalizada, HttpStatus.OK);
    }
}
