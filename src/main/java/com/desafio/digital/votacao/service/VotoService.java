package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.domain.Voto;
import com.desafio.digital.votacao.domain.client.StatusEnum;
import com.desafio.digital.votacao.domain.client.Users;
import com.desafio.digital.votacao.exception.BussinessException;
import com.desafio.digital.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    public List<Voto> findByIdPauta(Long idPauta) {
        return votoRepository.findByIdPauta(idPauta);
    }

    private void checkVotoPermitido(Voto voto) {
        sessaoService.checkSessaoAberta(voto.getIdPauta());
        checkAssociadoHabilitado(voto.getCpfAssociado());
        checkVotoUnico(voto.getIdPauta(), voto.getCpfAssociado());
    }

    private void checkAssociadoHabilitado(String cpfAssociado) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Users users = restTemplate
                    .getForObject("https://user-info.herokuapp.com/users/{cpf}", Users.class, cpfAssociado);

            if (StatusEnum.UNABLE_TO_VOTE.equals(Objects.requireNonNull(users).getStatus())) {
                throw new BussinessException(String.format("Usuário %s não habilitado a votar", cpfAssociado));
            }
        } catch (HttpClientErrorException ex) {
            throw new BussinessException(String.format("CPF %s inválido", cpfAssociado));
        }
    }

    private void checkVotoUnico(Long idPauta, String cpfAssociado) {
        if (Objects.nonNull(votoRepository.findByIdPautaAndCpfAssociado(idPauta, cpfAssociado))) {
            throw new BussinessException(String.format("Usuário %s já votou.", cpfAssociado));
        }
    }
}
