package com.siscontabil.siscontabil.repository;


import java.util.List;

import com.siscontabil.siscontabil.model.Setor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{
  
  List<Setor> findByNome(String nome);


  @Query("SELECT s from Setor s where s.id not in (select f.setor.id FROM FolhaPagamento f where f.competencia = :competencia)")
  List<Setor> allSetorNotContainsFolhaPagamentoByCompetencia(@Param("competencia") String competencia);

}
