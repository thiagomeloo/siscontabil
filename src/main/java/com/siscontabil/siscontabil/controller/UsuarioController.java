package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.service.serviceImplents.UsuarioServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

  private static final String HOME_PAGE = "redirect:/";

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

      try {
        usuarioService.save(usuario);
        redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o usuario");
        redirectAttributes.addAttribute("message_type", "success");
      } catch (Exception e) {
        redirectAttributes.addAttribute("message_text", "Erro ao cadastrar o usuario");
        redirectAttributes.addAttribute("message_type", "danger");
      }
    }

    return url;
  }

  @GetMapping("/usuario/inativar/{id}")
  public String getInativar(@PathVariable("id") long id, Model model, HttpSession session,
      RedirectAttributes redirectAttributes) {

    String url = auth.getUrl(session, HOME_PAGE, auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        Usuario usuario = usuarioService.findById(id);
        usuario.setStatus(false);
        usuarioService.save(usuario);
        redirectAttributes.addAttribute("message_text", "Sucesso ao inativar o usuario");
        redirectAttributes.addAttribute("message_type", "success");
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @GetMapping("/usuario/ativar/{id}")
  public String getAtivar(@PathVariable("id") long id, Model model, HttpSession session,
      RedirectAttributes redirectAttributes) {

    String url = auth.getUrl(session, HOME_PAGE, auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        Usuario usuario = usuarioService.findById(id);
        usuario.setStatus(true);
        usuarioService.save(usuario);
        redirectAttributes.addAttribute("message_text", "Sucesso ao ativar o usuario");
        redirectAttributes.addAttribute("message_type", "success");
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }
}
