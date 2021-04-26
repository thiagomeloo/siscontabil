package com.siscontabil.siscontabil.service;

import java.util.List;

import com.siscontabil.siscontabil.model.Usuario;

import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

  public List<Usuario> findAll();
  public Usuario save(Usuario usuario);
  public Usuario findById(Long id);
  Usuario findByLoginAndSenha(String login, String senha);
}
