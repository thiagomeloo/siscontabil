package com.siscontabil.siscontabil.repository;

import com.siscontabil.siscontabil.model.Movimentacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
  
}
