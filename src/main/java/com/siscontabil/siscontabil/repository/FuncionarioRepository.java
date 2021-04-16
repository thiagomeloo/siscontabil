package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
  
  @Query("select f from Funcionario f where f.funcao.setor.id = :idSetor")
  List<Funcionario> allFuncionarioBySetor(@Param("idSetor") Long idSetor);

}
