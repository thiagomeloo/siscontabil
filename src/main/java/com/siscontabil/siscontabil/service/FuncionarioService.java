package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Funcionario;

import org.springframework.stereotype.Service;

@Service
public interface FuncionarioService {
    
    public Funcionario save(Funcionario funcionario);
    public Funcionario findById(Long id);
    public List<Funcionario> findAll();
    public List<Funcionario> allFuncionarioBySetor(Long idSetor);
    
}
