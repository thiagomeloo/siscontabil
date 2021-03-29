package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Setor;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SetorController {
  

  @Autowired
  SetorServiceImpl setorService;

  @GetMapping("/setor")
  public ModelAndView getSetors(){
    ModelAndView mv = new ModelAndView("pages/listaSetor");
      mv.addObject("allSetors", setorService.findAll());
      return mv;
  }

  @GetMapping("/setor/create")
  public String getFormCreate(){
      return "pages/formSetor";
  }
  
  @PostMapping(value = "/setor/create")
  public String saveSetor(Setor setor){
      
      setorService.save(setor); 
      return "redirect:/";
  }


}
