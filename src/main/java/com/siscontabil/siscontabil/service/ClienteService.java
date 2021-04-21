package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Cliente;

import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
    public List<Cliente> findAll();
    public Cliente save(Cliente cliente);
    public Cliente findById(Long id);
    public List<Cliente> allClienteAtivo();
    public List<Cliente> allClienteInativo();
}
