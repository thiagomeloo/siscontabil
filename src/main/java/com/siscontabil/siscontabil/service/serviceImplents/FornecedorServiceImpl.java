package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.service.FornecedorService;
import com.siscontabil.siscontabil.model.Fornecedor;
import com.siscontabil.siscontabil.repository.FornecedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    FornecedorRepository repository;
  

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    @Override
    public Fornecedor findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    
    
}
