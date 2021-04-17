package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Produto;
import com.siscontabil.siscontabil.repository.ProdutoRepository;
import com.siscontabil.siscontabil.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Override
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Override
    public Produto findById( Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    
}
