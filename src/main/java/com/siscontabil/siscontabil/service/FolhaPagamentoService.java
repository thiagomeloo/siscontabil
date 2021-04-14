package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;

import org.springframework.stereotype.Service;

@Service
public interface FolhaPagamentoService {

  public List<FolhaPagamento> findAll();
  public FolhaPagamento save(FolhaPagamento setor);
  public FolhaPagamento findById(Long id);
  
}
