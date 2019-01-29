package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.out.SessaoOut;
import com.desafio.digital.votacao.entity.Sessao;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SessaoToSessaoOutConverter implements Converter<Sessao, SessaoOut> {

    private final ModelMapper modelMapper;

    public SessaoToSessaoOutConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SessaoOut convert(Sessao sessao) {
        SessaoOut sessaoOut = modelMapper.map(sessao, SessaoOut.class);

        if (Objects.isNull(sessaoOut)) {
            throw new IllegalArgumentException("Objeto Sessao não pode ser convertido em SessaoOut");
        }

        return sessaoOut;
    }
}
