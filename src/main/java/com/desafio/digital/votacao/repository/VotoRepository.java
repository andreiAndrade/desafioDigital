package com.desafio.digital.votacao.repository;

import com.desafio.digital.votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Voto findByIdPautaAndCpfAssociado(Long idSessao, String cpfAssociado);

    List<Voto> findByIdPauta(Long idSessao);
}
