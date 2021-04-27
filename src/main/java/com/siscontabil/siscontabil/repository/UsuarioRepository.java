package com.siscontabil.siscontabil.repository;

import com.siscontabil.siscontabil.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLoginAndSenha(String login, String senha);
}
