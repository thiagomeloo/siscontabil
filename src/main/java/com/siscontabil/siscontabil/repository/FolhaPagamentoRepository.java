package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long>{
    
    @Query("select f from FolhaPagamento f where f.competencia like '%'||:competencia")
    List<FolhaPagamento> allFolhaPagamentoCompetencia(@Param("competencia") String competencia);

    @Query("select f from FolhaPagamento f where f.competencia like '%'||:competencia")
    List<FolhaPagamento> allFolhaPagamentoMes(@Param("competencia") String competencia);


}
