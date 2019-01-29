package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.domain.Sessao;
import com.desafio.digital.votacao.domain.Voto;
import com.desafio.digital.votacao.exception.BussinessException;
import com.desafio.digital.votacao.exception.ResourceNotFoundException;
import com.desafio.digital.votacao.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final VotoService votoService;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository, VotoService votoService) {
        this.sessaoRepository = sessaoRepository;
        this.votoService = votoService;
    }

    public Sessao criarSessao(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }


    public Sessao finalizarSessao(Long idSessao) {
        Sessao sessao = findById(idSessao);
        List<Voto> votoList = votoService.findByIdPauta(sessao.getIdPauta());
        sessao.setVotosFavor(votoService.countVotosFavor(votoList));
        sessao.setVotosContra(votoService.countVotosContra(votoList));
        sessao.setTotalVotos(votoList.size());

        return sessaoRepository.save(sessao);
    }

    public void checkSessaoAbertaByIdPauta(Long idPauta) {
        if (!isSessaoAbertaByIdPauta(idPauta)) {
            throw new BussinessException(String.format("Sessão para a pauta %s não está aberta.", idPauta));
        }
    }

    private boolean isSessaoAbertaByIdPauta(Long idPauta) {
        Sessao sessao = findByIdPauta(idPauta);
        return sessao.getInicioSessao().plusMinutes(sessao.getTempoDuracaoSessao()).isAfter(LocalDateTime.now());
    }

    private Sessao findById(Long idSessao) {
        return sessaoRepository.findById(idSessao).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Sessão com o id %d não encontrada.", idSessao)));
    }

    private Sessao findByIdPauta(Long idPauta) {
        Sessao sessao = sessaoRepository.findByIdPauta(idPauta);

        if (Objects.isNull(sessao)) {
            throw new ResourceNotFoundException(String.format("Sessão para a pauta %d não encontrada.", idPauta));
        }

        return sessao;
    }
}
