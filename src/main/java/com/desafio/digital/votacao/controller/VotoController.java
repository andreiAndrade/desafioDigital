package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.ApiVersion;
import com.desafio.digital.votacao.domain.Sessao;
import com.desafio.digital.votacao.domain.Voto;
import com.desafio.digital.votacao.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Serviço para Manipulação de Voto", description = "Operações de Voto")
@RestController
@RequestMapping(value = "/voto")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @ApiOperation(value = "Cria um voto", response = Sessao.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Voto criado com sucesso"),
            @ApiResponse(code = 400, message = "Campos informados não atrendem as regras de negócio"),
            @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 404, message = "Sessão não encontrada para o id da pauta informado")
    })
    @PostMapping(produces = ApiVersion.V1)
    public ResponseEntity<Object> criarVoto(
            @ApiParam(value = "Voto que será salvo no banco de dados", required = true) @Valid @RequestBody Voto voto) {
        Voto votoCriado = votoService.votar(voto);
        return new ResponseEntity<>(votoCriado, HttpStatus.CREATED);
    }
}
