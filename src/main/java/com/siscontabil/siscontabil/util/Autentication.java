package com.siscontabil.siscontabil.util;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;

public class Autentication {
  
  public boolean isAutenticated(HttpSession session){
  
    if(session.getAttribute("user") != null){
      return true;
    }else{
      return false;
    }

  }

  public String getPermission(HttpSession session){
    if(isAutenticated(session)){
      Usuario user = (Usuario) session.getAttribute("user");
      return user.getTipoUsuario();
    }else{
      return null;
    }
  }

  public String getUrl(HttpSession session, String pageSucess){
    if(isAutenticated(session)){
      return pageSucess;
    }
    return (getRouterLogin());
  }

  public String getRouterLogin(){
    return "redirect:/login";
  }

}
