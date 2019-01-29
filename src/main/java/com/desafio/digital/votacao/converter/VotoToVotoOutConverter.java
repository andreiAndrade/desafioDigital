package com.desafio.digital.votacao.converter;

import com.desafio.digital.votacao.domain.out.VotoOut;
import com.desafio.digital.votacao.entity.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VotoToVotoOutConverter implements Converter<Voto, VotoOut> {

    private final ModelMapper modelMapper;

    public VotoToVotoOutConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VotoOut convert(Voto voto) {
        VotoOut votoOut = modelMapper.map(voto, VotoOut.class);

        if (Objects.isNull(votoOut)) {
            throw new IllegalArgumentException("Objeto Voto não pode ser convertido em VotoOut");
        }

        return votoOut;
    }
}
