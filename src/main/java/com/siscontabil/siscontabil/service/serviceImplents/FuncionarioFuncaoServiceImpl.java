package com.siscontabil.siscontabil.service.serviceImplents;

import com.siscontabil.siscontabil.model.FuncionarioFuncao;
import com.siscontabil.siscontabil.repository.FuncionarioFuncaoRepository;
import com.siscontabil.siscontabil.service.FuncionarioFuncaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioFuncaoServiceImpl implements FuncionarioFuncaoService {

  @Autowired
  FuncionarioFuncaoRepository repository;

  @Override
  public FuncionarioFuncao save(FuncionarioFuncao funcionarioFuncao) {
    return repository.save(funcionarioFuncao);
  }
  
}
