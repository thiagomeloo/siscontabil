package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Setor;

import org.springframework.stereotype.Service;

@Service
public interface SetorService {

  public List<Setor> findAll();
  public Setor save(Setor setor);
  public Setor findById(Long id);
  
}
