package com.desafio.digital.votacao.controller;

import com.desafio.digital.votacao.domain.in.SessaoIn;
import com.desafio.digital.votacao.entity.Sessao;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SessaoControllerTest {

    private SessaoIn sessao = new SessaoIn();

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        sessao.setIdPauta(1L);
        sessao.setTempoDuracaoSessao(2);
    }

    @Test
    public void postSessaoNoBodyBadRequest() throws Exception {

        mvc.perform(post("/sessao")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSessaoNoFieldsBadRequest() throws Exception {

        mvc.perform(post("/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize(new Sessao())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postSessaoCreated() throws Exception {

        mvc.perform(post("/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialize(sessao)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void putSessaoOk() throws Exception {

        mvc.perform(put("/sessao?sessao=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
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

