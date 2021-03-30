package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.repository.UsuarioRepository;
import com.siscontabil.siscontabil.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  UsuarioRepository repository;

  @Override
  public List<Usuario> findAll() {
    return repository.findAll();
  }

  @Override
  public Usuario save(Usuario usuario) {
    return repository.save(usuario);
  }

  @Override
  public Usuario findById(Long id) {
    return repository.findById(id).get();
  }


  
}
