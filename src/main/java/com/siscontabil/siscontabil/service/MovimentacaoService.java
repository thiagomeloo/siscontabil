package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Movimentacao;
import org.springframework.stereotype.Service;

@Service
public interface MovimentacaoService {

  public List<Movimentacao> findAll();
  public Movimentacao save(Movimentacao movimentacao);
  public Movimentacao findById(Long id);
}
