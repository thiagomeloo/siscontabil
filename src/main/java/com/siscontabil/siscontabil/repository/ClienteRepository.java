package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query("select c from Cliente c where c.status = true")
    List<Cliente> allClienteAtivo();

    @Query("select c from Cliente c where c.status = false")
    List<Cliente> allClienteInativo();
}
