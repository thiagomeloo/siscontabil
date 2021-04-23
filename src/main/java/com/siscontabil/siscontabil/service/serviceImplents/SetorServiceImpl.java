package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Setor;
import com.siscontabil.siscontabil.repository.SetorRepository;
import com.siscontabil.siscontabil.service.SetorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorServiceImpl implements SetorService {

  @Autowired
  SetorRepository repository;

  @Override
  public Setor save(Setor setor) {
    return repository.save(setor);
  }

  @Override
  public List<Setor> findAll() {
    return repository.findAll();
  }

  @Override
  public Setor findById(Long id) {
    return repository.findById(id).get();
  }

  @Override
  public List<Setor> findByNome(String nome) {
    return repository.findByNome(nome);
  }

  @Override
  public List<Setor> allSetorNotContainsFolhaPagamentoByCompetencia(String competencia) {
    return repository.allSetorNotContainsFolhaPagamentoByCompetencia(competencia);
  }

  
  
}
