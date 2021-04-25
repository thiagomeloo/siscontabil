package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;

import org.springframework.stereotype.Service;

@Service
public interface FolhaPagamentoService {

  public List<FolhaPagamento> findAll();
  public FolhaPagamento save(FolhaPagamento folhaPagamento);
  public FolhaPagamento findById(Long id);
  public List<FolhaPagamento> allFolhaPagamentoCompetencia( String competencia);
  public List<FolhaPagamento> allFolhaPagamentoMes(String competencia);
  public List<FolhaPagamento> allFolhaPagar();
  public List<FolhaPagamento> allFolhaPago();
}
