package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.config.ApiVersion;
import com.desafio.digital.votacao.domain.in.PautaIn;
import com.desafio.digital.votacao.entity.Pauta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PautaControllerTest {

    private PautaIn pauta = new PautaIn();

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        pauta.setTitulo("Titulo");
        pauta.setDescricao("Descricao");
    }

    @Test
    public void postPautaNoBodyBadRequest() throws Exception {

        mvc.perform(post("/pauta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postPautaNoFieldsBadRequest() throws Exception {

        mvc.perform(post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize(new Pauta())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postPautaCreated() throws Exception {

        mvc.perform(post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize(pauta)))
                .andExpect(status().isCreated());
    }

    @Test
    public void postPautaOk() throws Exception {

        mvc.perform(post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(ApiVersion.V1)
                .content(serialize(pauta)))
                .andExpect(status().isOk());
    }

    public static String serialize(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

