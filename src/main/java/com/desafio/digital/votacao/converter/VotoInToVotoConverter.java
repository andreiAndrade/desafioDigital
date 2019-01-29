package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.in.VotoIn;
import com.desafio.digital.votacao.entity.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VotoInToVotoConverter implements Converter<VotoIn, Voto> {

    private final ModelMapper modelMapper;

    public VotoInToVotoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Voto convert(VotoIn votoIn) {
        Voto voto = modelMapper.map(votoIn, Voto.class);

        if (Objects.isNull(voto)) {
            throw new IllegalArgumentException("Objeto VotoIn não pode ser convertido em Voto");
        }

        return voto;
    }
}
