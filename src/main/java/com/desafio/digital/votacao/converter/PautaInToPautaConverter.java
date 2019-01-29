package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.in.PautaIn;
import com.desafio.digital.votacao.entity.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PautaInToPautaConverter implements Converter<PautaIn, Pauta> {

    private final ModelMapper modelMapper;

    public PautaInToPautaConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Pauta convert(PautaIn pautaIn) {
        Pauta pauta = modelMapper.map(pautaIn, Pauta.class);

        if (Objects.isNull(pauta)) {
            throw new IllegalArgumentException("Objeto PautaIn não pode ser convertido em Pauta");
        }

        return pauta;
    }
}
