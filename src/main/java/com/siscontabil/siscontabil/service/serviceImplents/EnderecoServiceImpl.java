package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.repository.EnderecoRepository;
import com.siscontabil.siscontabil.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService{
    
    @Autowired
  EnderecoRepository repository;

  @Override
  public Endereco save(Endereco endereco) {
    return repository.save(endereco);
  }

  @Override
  public List<Endereco> findAll() {
    return repository.findAll();
  }

  @Override
  public Endereco findById(Long id) {
    return repository.findById(id).get();
  }
}
