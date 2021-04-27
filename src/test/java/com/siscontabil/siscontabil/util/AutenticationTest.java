package com.siscontabil.siscontabil.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AutenticationTest {

  @MockBean
  Autentication auth;

  HttpSession session = mock(HttpSession.class);

  @Test
  public void deveRetornarSucesso_QuandoVerificarUsuarioAutenticado() {

    Usuario usuario = new Usuario();
    usuario.setId(1);
    usuario.setLogin("user");
    usuario.setSenha("123");
    usuario.setTipoUsuario("admin");
    usuario.setStatus(true);

    doReturn(usuario).when(session).getAttribute("user");

    doReturn(true).when(auth).isAutenticated(session);

    assertEquals(true, auth.isAutenticated(session));

  }

  @Test
  public void deveRetornarSucesso_QuandoVerificarUsuarioNaoAutenticado() {

    doReturn(null).when(session).getAttribute("user");

    doReturn(false).when(auth).isAutenticated(session);

    assertEquals(false, auth.isAutenticated(session));

  }

}
