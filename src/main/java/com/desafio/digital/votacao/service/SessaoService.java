package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.converter.SessaoInToSessaoConverter;
import com.desafio.digital.votacao.converter.SessaoToSessaoOutConverter;
import com.desafio.digital.votacao.domain.in.SessaoIn;
import com.desafio.digital.votacao.domain.out.SessaoOut;
import com.desafio.digital.votacao.entity.Sessao;
import com.desafio.digital.votacao.entity.Voto;
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
    private final SessaoInToSessaoConverter sessaoInToSessaoConverter;
    private final SessaoToSessaoOutConverter sessaoToSessaoOutConverter;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository, VotoService votoService,
            SessaoInToSessaoConverter sessaoInToSessaoConverter,
            SessaoToSessaoOutConverter sessaoToSessaoOutConverter) {
        this.sessaoRepository = sessaoRepository;
        this.votoService = votoService;
        this.sessaoInToSessaoConverter = sessaoInToSessaoConverter;
        this.sessaoToSessaoOutConverter = sessaoToSessaoOutConverter;
    }

    public SessaoOut criarSessao(SessaoIn sessaoIn) {
        Sessao sessao = sessaoInToSessaoConverter.convert(sessaoIn);

        if (Objects.isNull(sessao.getTempoDuracaoSessao())) {
            sessao.setTempoDuracaoSessao(1);
        }

        sessao.setInicioSessao(LocalDateTime.now());

        Sessao sessaoCriada = sessaoRepository.save(sessao);
        return sessaoToSessaoOutConverter.convert(sessaoCriada);
    }


    public SessaoOut finalizarSessao(Long idSessao) {
        Sessao sessao = findById(idSessao);
        List<Voto> votoList = votoService.findByIdPauta(sessao.getIdPauta());
        sessao.setVotosFavor(votoService.countVotosFavor(votoList));
        sessao.setVotosContra(votoService.countVotosContra(votoList));
        sessao.setTotalVotos(votoList.size());

        Sessao sessaoFinalizada = sessaoRepository.save(sessao);
        return sessaoToSessaoOutConverter.convert(sessaoFinalizada);
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
