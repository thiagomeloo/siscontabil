package com.siscontabil.siscontabil.service;

import com.siscontabil.siscontabil.model.FuncionarioFuncao;

import org.springframework.stereotype.Service;

@Service
public interface FuncionarioFuncaoService {

  public FuncionarioFuncao save(FuncionarioFuncao funcionarioFuncao);
  
}
