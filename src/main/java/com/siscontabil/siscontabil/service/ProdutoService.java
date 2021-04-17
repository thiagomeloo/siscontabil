package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Produto;

import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {

    public List<Produto> findAll();
    public Produto findById( Long id);
    public Produto save(Produto produto);
}
