package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  
  Autentication auth = new Autentication();

  @RequestMapping("/")
  public String getIndex(HttpSession session){
   if(auth.isAutenticated(session)){
    return "pages/index";
   }
   return "redirect:/login";

  }
  @RequestMapping("/login")
  public String getLogin(HttpSession session){
    if(auth.isAutenticated(session)){
      return "redirect:/";
    }
    return "pages/login";
  }

  @RequestMapping("/login/teste")
  public String getTeste(HttpSession session){
    if(auth.isAutenticated(session)){
      session.removeAttribute("user");
      return "pages/login";
    }

    Usuario user = new Usuario();
    user.setTipoUsuario("Aministrador");

    session.setAttribute("user", user);

    return "redirect:/";

  }

}



