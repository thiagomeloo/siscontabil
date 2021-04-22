package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.repository.FuncionarioRepository;
import com.siscontabil.siscontabil.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

  @Autowired
  FuncionarioRepository repository;

  @Override
  public Funcionario save(Funcionario funcionario) {
    return repository.save(funcionario);
  }

  @Override
  public List<Funcionario> findAll() {
    return repository.findAll();
  }

  @Override
  public Funcionario findById(Long id) {
    return repository.findById(id).get();
  }

  @Override
  public List<Funcionario> allFuncionarioBySetor(Long idSetor) {
    return repository.allFuncionarioBySetor(idSetor);
  }

  @Override
  public List<Object[]> allFuncionarioAndSalarioBySetor(Long idSetor) {
    
    return repository.allFuncionarioAndSalarioBySetor(idSetor);
  }

  
  
}
