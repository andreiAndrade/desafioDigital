package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.in.SessaoIn;
import com.desafio.digital.votacao.entity.Sessao;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SessaoInToSessaoConverter implements Converter<SessaoIn, Sessao> {

    private final ModelMapper modelMapper;

    public SessaoInToSessaoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Sessao convert(SessaoIn sessaoIn) {
        Sessao sessao = modelMapper.map(sessaoIn, Sessao.class);

        if (Objects.isNull(sessao)) {
            throw new IllegalArgumentException("Objeto SessaoIn não pode ser convertido em Sessao");
        }

        return sessao;
    }
}
