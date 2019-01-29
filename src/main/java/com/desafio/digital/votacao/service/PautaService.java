package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.domain.Pauta;
import com.desafio.digital.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }


    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
