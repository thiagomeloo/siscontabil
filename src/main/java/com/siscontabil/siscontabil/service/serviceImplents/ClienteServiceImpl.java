package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Cliente;
import com.siscontabil.siscontabil.repository.ClienteRepository;
import com.siscontabil.siscontabil.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Cliente> allClienteAtivo() {
        
        return repository.allClienteAtivo();
    }

    @Override
    public List<Cliente> allClienteInativo() {
        return repository.allClienteInativo();
    }
    
}
