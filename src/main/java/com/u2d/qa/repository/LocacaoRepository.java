package com.u2d.qa.repository;

import com.u2d.qa.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Eager
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query("FROM Locacao")
    List<Locacao> obterLocacoesPendentes();
}
