package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.service.serviceImplents.UsuarioServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

  @Autowired
  UsuarioServiceImpl usuarioService;

  Autentication auth = new Autentication();

  @GetMapping("/usuario")
  public String getUsuarios(Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/listaUsuario", auth.ADMIN);

    if (auth.isAutenticated(session)) {
      model.addAttribute("allUsers", usuarioService.findAll());
    }

    return url;
  }

  @GetMapping("/usuario/create")
  public String getFormCreate(HttpSession session) {
    return (auth.getUrl(session, "pages/formUsuario", auth.ADMIN));
  }

  @PostMapping(value = "/usuario/create")
  public String saveUser(Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, "redirect:/", auth.ADMIN);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o usuario");
      redirectAttributes.addAttribute("message_type", "success");

      usuarioService.save(usuario);
    }

    return url;
  }

}
