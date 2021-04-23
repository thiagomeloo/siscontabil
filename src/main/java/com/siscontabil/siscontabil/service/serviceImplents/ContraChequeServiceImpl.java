package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.ContraCheque;
import com.siscontabil.siscontabil.repository.ContraChequeRepository;
import com.siscontabil.siscontabil.service.ContraChequeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContraChequeServiceImpl implements ContraChequeService{

  @Autowired
  ContraChequeRepository repository;

  @Override
  public List<ContraCheque> findAll() {
    
    return repository.findAll();
  }

  @Override
  public ContraCheque save(ContraCheque contraCheque) {
    
    return repository.save(contraCheque);
  }

  @Override
  public ContraCheque findById(Long id) {
    
    return repository.findById(id).get();
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
  
}
