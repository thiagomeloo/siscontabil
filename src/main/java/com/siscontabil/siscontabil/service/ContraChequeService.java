package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.ContraCheque;
import org.springframework.stereotype.Service;

@Service
public interface ContraChequeService {

  public List<ContraCheque> findAll();
  public ContraCheque save(ContraCheque contraCheque);
  public ContraCheque findById(Long id);
  public void deleteById(Long id);
  
}
