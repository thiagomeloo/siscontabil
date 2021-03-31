package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Usuario;
import com.siscontabil.siscontabil.service.serviceImplents.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
  

  @Autowired
  UsuarioServiceImpl usuarioService;

  @GetMapping("/usuario")
  public ModelAndView getUsuarios(){
    ModelAndView mv = new ModelAndView("pages/listaUsuario");
      mv.addObject("allUsers", usuarioService.findAll());
      return mv;
  }

  @GetMapping("/usuario/create")
  public String getFormCreate(){
      return "pages/formUsuario";
  }
  
  @PostMapping(value = "/usuario/create")
  public String saveUser(Usuario usuario){
      usuarioService.save(usuario); 
      return "redirect:/";
  }


}
