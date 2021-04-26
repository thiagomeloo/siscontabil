package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/login")
  public String getTeste(HttpSession session, Usuario usuario){
    // busca usuario = usuario
    Usuario user = new Usuario();
    user.setTipoUsuario("admin");
    session.setAttribute("user", user);
    return "redirect:/";

  }

  @RequestMapping("/logout")
  public String getLogout(HttpSession session){
    if(auth.isAutenticated(session)){
      session.removeAttribute("user");
    }
    return "redirect:/login";
  }
}



