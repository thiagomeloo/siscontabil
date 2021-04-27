package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.Movimentacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("select m from Movimentacao m where m.dataMovimentacao BETWEEN '2021-04-25' and '2021-04-26'")
    List<Movimentacao> allMovimentacaoDate(@Param("dataInicial") String dataInicial,
            @Param("dataFinal") String dataFinal);

}
