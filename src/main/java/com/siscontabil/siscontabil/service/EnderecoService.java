package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Endereco;

import org.springframework.stereotype.Service;


@Service
public interface EnderecoService {

    public Endereco save(Endereco endereco);
    public Endereco findById(Long id);
    public List<Endereco> findAll();
}
