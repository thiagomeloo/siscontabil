package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.service.serviceImplents.UsuarioServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

  @Autowired
  UsuarioServiceImpl usuarioService;

  Autentication auth = new Autentication();

  @RequestMapping("/")
  public String getIndex(HttpSession session) {
    if (auth.isAutenticated(session)) {
      return "pages/index";
    }
    return "redirect:/login";

  }

  @RequestMapping("/login")
  public String getLogin(HttpSession session) {
    if (auth.isAutenticated(session)) {
      return "redirect:/";
    }
    return "pages/login";
  }

  @PostMapping("/login")
  public String getTeste(HttpSession session, Usuario usuario, RedirectAttributes redirectAttributes) {

    Usuario userRetorno = usuarioService.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
    if (userRetorno != null) {
      session.setAttribute("user", userRetorno);

      redirectAttributes.addAttribute("message_text", "Sucesso ao realizar login!");
      redirectAttributes.addAttribute("message_type", "success");
      return "redirect:/";
    }
    redirectAttributes.addAttribute("message_text", "Login ou Senha Incorretos!");
    redirectAttributes.addAttribute("message_type", "danger");
    return "redirect:/login";
  }

  @RequestMapping("/logout")
  public String getLogout(HttpSession session, RedirectAttributes redirectAttributes) {
    if (auth.isAutenticated(session)) {
      session.removeAttribute("user");
      redirectAttributes.addAttribute("message_text", "Logout efetuado com Sucesso!");
      redirectAttributes.addAttribute("message_type", "success");
    }

    return "redirect:/login";
  }
}
