package com.siscontabil.siscontabil.service;

import com.siscontabil.siscontabil.model.Fornecedor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FornecedorService {
    public Fornecedor save(Fornecedor fornecedor);
    public Fornecedor findById(Long id);
    public List<Fornecedor> findAll();
}
