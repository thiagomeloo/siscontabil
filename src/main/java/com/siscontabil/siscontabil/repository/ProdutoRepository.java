package com.siscontabil.siscontabil.repository;

import com.siscontabil.siscontabil.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
    
}
