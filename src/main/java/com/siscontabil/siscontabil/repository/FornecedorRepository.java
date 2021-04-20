package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    @Query("select f from Fornecedor f where f.status = true")
    List<Fornecedor> allFornecedorAtivo();

}
