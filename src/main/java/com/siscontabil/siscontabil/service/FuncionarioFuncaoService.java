package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.FuncionarioFuncao;

import org.springframework.stereotype.Service;

@Service
public interface FuncionarioFuncaoService {

  public List<FuncionarioFuncao> findAll();
  public FuncionarioFuncao save(FuncionarioFuncao funcionarioFuncao);
  public FuncionarioFuncao findById(Long id);
  
}
