package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.config.ApiVersion;
import com.desafio.digital.votacao.domain.in.PautaIn;
import com.desafio.digital.votacao.domain.out.PautaOut;
import com.desafio.digital.votacao.entity.Pauta;
import com.desafio.digital.votacao.service.PautaService;
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

@Api(value = "Serviço para Manipulação de Pauta")
@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @ApiOperation(value = "Cria uma pauta", response = Pauta.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta criada com sucesso"),
            @ApiResponse(code = 400, message = "Campos informados não atrendem as regras de negócio"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping(produces = ApiVersion.V1)
    public ResponseEntity<PautaOut> criarPautaV1(
            @ApiParam(value = "Pauta que será salva no banco de dados", required = true) @Valid @RequestBody PautaIn pautaIn) {
        PautaOut pautaOut = pautaService.criarPauta(pautaIn);
        return new ResponseEntity<>(pautaOut, HttpStatus.OK);
    }

    @ApiOperation(value = "Cria uma pauta", response = Pauta.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta criada com sucesso"),
            @ApiResponse(code = 400, message = "Campos informados não atrendem as regras de negócio"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping(produces = ApiVersion.V2)
    public ResponseEntity<PautaOut> criarPautaV2(
            @ApiParam(value = "Pauta que será salva no banco de dados", required = true) @Valid @RequestBody PautaIn pautaIn) {
        PautaOut pautaOut = pautaService.criarPauta(pautaIn);
        return new ResponseEntity<>(pautaOut, HttpStatus.OK);
    }
}
