package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.out.PautaOut;
import com.desafio.digital.votacao.entity.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PautaToPautaOutConverter implements Converter<Pauta, PautaOut> {

    private final ModelMapper modelMapper;

    public PautaToPautaOutConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PautaOut convert(Pauta pauta) {
        PautaOut pautaOut = modelMapper.map(pauta, PautaOut.class);

        if (Objects.isNull(pautaOut)) {
            throw new IllegalArgumentException("Objeto PautaOut não pode ser convertido em Pauta");
        }

        return pautaOut;
    }
}
