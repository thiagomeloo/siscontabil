package com.siscontabil.siscontabil.util;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;

import lombok.Data;

@Data
public class Autentication {

  public static final String ADMIN = "admin";
  public static final String FINANCEIRO = "financeiro";
  public static final String CONTABILIDADE = "contabilidade";
  public static final String FATURAMENTO = "faturamento";

  private static final String ROUTER_LOGIN = "redirect:/login";

  public boolean isAutenticated(HttpSession session) {

    if(session.getAttribute("user") != null) {

      return true;

    } else {

      return false;
      
    }

  }

  public String getUrl(HttpSession session, String pageSucess, String permission) {
    if (isAutenticated(session)) {

      Usuario user = (Usuario) session.getAttribute("user");

      String tipo = user.getTipoUsuario();

      if (tipo.equals(permission) || tipo.equals(ADMIN)) {
        return pageSucess;
      }
      return ROUTER_LOGIN;
    }
    return ROUTER_LOGIN;
  }

  public String getUrl(HttpSession session, String pageSucess, String[] permission) {
    if (isAutenticated(session)) {

      Usuario user = (Usuario) session.getAttribute("user");
      for (int i = 0; i < permission.length; i++) {
        String tipo = user.getTipoUsuario();
        if (tipo.equals(permission[i]) || tipo.equals(ADMIN)) {
          return pageSucess;
        }
      }
      return ROUTER_LOGIN;
    }
    return ROUTER_LOGIN;
  }

}
