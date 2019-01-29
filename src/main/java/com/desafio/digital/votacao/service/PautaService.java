package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.converter.PautaInToPautaConverter;
import com.desafio.digital.votacao.converter.PautaToPautaOutConverter;
import com.desafio.digital.votacao.domain.in.PautaIn;
import com.desafio.digital.votacao.domain.out.PautaOut;
import com.desafio.digital.votacao.entity.Pauta;
import com.desafio.digital.votacao.exception.ResourceNotFoundException;
import com.desafio.digital.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final PautaInToPautaConverter pautaInToPautaConverter;
    private final PautaToPautaOutConverter pautaToPautaOutConverter;

    @Autowired
    public PautaService(PautaRepository pautaRepository, PautaInToPautaConverter pautaInToPautaConverter,
            PautaToPautaOutConverter pautaToPautaOutConverter) {
        this.pautaRepository = pautaRepository;
        this.pautaInToPautaConverter = pautaInToPautaConverter;
        this.pautaToPautaOutConverter = pautaToPautaOutConverter;
    }

    public PautaOut criarPauta(PautaIn pautaIn) {
        Pauta pauta = pautaInToPautaConverter.convert(pautaIn);
        Pauta pautaCriada = pautaRepository.save(pauta);

        return pautaToPautaOutConverter.convert(pautaCriada);
    }

    public void checkExistPautaById(Long idPauta) {
        if (!pautaRepository.findById(idPauta).isPresent()) {
            throw new ResourceNotFoundException(String.format("Pauta com o id %s não encontrada", idPauta));
        }
    }
}
