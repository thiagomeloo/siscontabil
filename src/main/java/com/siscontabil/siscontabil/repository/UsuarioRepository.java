package com.siscontabil.siscontabil.repository;

import java.util.List;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.service.serviceImplents.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    

    Usuario findByLoginAndSenha(String login, String senha);
}
