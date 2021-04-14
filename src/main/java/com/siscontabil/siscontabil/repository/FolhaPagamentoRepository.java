package com.siscontabil.siscontabil.repository;

import com.siscontabil.siscontabil.model.FolhaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long>{
  

}
