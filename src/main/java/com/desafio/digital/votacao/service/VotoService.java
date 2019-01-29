package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.domain.Voto;
import com.desafio.digital.votacao.exception.BussinessException;
import com.desafio.digital.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;

    @Autowired
    public VotoService(VotoRepository votoRepository, @Lazy SessaoService sessaoService) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
    }

    public Voto votar(Voto voto) {
        checkVotoPermitido(voto);

        return votoRepository.save(voto);
    }

    private void checkVotoPermitido(Voto voto) {
        sessaoService.checkSessaoAberta(voto.getIdPauta());
        checkVotoUnico(voto.getIdPauta(), voto.getCpfAssociado());
    }

    private void checkVotoUnico(Long idPauta, String cpfAssociado) {
        if (Objects.nonNull(votoRepository.findByIdPautaAndCpfAssociado(idPauta, cpfAssociado))) {
            throw new BussinessException(String.format("Usuário %s já votou.", cpfAssociado));
        }
    }

    public List<Voto> findByIdPauta(Long idPauta) {
        return votoRepository.findByIdPauta(idPauta);
    }
}
