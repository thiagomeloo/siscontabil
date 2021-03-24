package com.siscontabil.siscontabil.service.serviceImplents;

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
  
}
