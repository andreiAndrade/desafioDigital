package com.desafio.digital.votacao.repository;

import com.desafio.digital.votacao.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Sessao findByIdPauta(Long idPauta);
}
