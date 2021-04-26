package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Movimentacao;
import com.siscontabil.siscontabil.repository.MovimentacaoRepository;
import com.siscontabil.siscontabil.service.MovimentacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

  @Autowired
  MovimentacaoRepository repository;

  @Override
  public List<Movimentacao> findAll() {
    return repository.findAll();
  }

  @Override
  public Movimentacao save(Movimentacao movimentacao) {
    return repository.save(movimentacao);
  }

  @Override
  public Movimentacao findById(Long id) {
    return repository.findById(id).get();
  }

 
  
  
}
