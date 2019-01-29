package com.desafio.digital.votacao.service;

import com.desafio.digital.votacao.converter.VotoInToVotoConverter;
import com.desafio.digital.votacao.converter.VotoToVotoOutConverter;
import com.desafio.digital.votacao.domain.client.StatusEnum;
import com.desafio.digital.votacao.domain.client.Users;
import com.desafio.digital.votacao.domain.in.VotoIn;
import com.desafio.digital.votacao.domain.out.VotoOut;
import com.desafio.digital.votacao.entity.Voto;
import com.desafio.digital.votacao.entity.enums.VotoEnum;
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
    private final VotoInToVotoConverter votoInToVotoConverter;
    private final VotoToVotoOutConverter votoToVotoOutConverter;

    @Autowired
    public VotoService(VotoRepository votoRepository, @Lazy SessaoService sessaoService,
            VotoInToVotoConverter votoInToVotoConverter, VotoToVotoOutConverter votoToVotoOutConverter) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
        this.votoInToVotoConverter = votoInToVotoConverter;
        this.votoToVotoOutConverter = votoToVotoOutConverter;
    }

    public VotoOut votar(VotoIn votoIn) {
        Voto voto = votoInToVotoConverter.convert(votoIn);
        checkVotoPermitido(voto);

        Voto votoCriado = votoRepository.save(voto);
        return votoToVotoOutConverter.convert(votoCriado);
    }

    public List<Voto> findByIdPauta(Long idPauta) {
        return votoRepository.findByIdPauta(idPauta);
    }

    public Long countVotosFavor(List<Voto> votoList) {
        return votoList.stream().filter(voto -> VotoEnum.SIM.equals(voto.getAceite())).count();
    }

    public Long countVotosContra(List<Voto> votoList) {
        return votoList.stream().filter(voto -> VotoEnum.NAO.equals(voto.getAceite())).count();
    }

    private void checkVotoPermitido(Voto voto) {
        sessaoService.checkSessaoAbertaByIdPauta(voto.getIdPauta());
        checkVotoUnico(voto.getIdPauta(), voto.getCpfAssociado());
        checkAssociadoHabilitado(voto.getCpfAssociado());
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
