package com.siscontabil.siscontabil.repository;


import java.util.List;

import com.siscontabil.siscontabil.model.Setor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{
  
  List<Setor> findByNome(String nome);

}
