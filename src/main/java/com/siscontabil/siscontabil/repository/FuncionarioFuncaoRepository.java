package com.siscontabil.siscontabil.repository;


import com.siscontabil.siscontabil.model.FuncionarioFuncao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FuncionarioFuncaoRepository extends JpaRepository<FuncionarioFuncao, Long>{
  

}
