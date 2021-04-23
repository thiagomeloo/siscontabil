package com.siscontabil.siscontabil.repository;
import com.siscontabil.siscontabil.model.ContraCheque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ContraChequeRepository extends JpaRepository<ContraCheque, Long>{
  
}
