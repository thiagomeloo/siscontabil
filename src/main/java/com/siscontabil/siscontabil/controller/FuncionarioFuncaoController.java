package com.siscontabil.siscontabil.controller;


import com.siscontabil.siscontabil.model.FuncionarioFuncao;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioFuncaoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioFuncaoController {
  

  @Autowired
  FuncionarioFuncaoServiceImpl funcionarioFuncaoService;

  @Autowired
  SetorServiceImpl setorService;


  @GetMapping("/funcionario/funcao/create")
  public ModelAndView getFormCreate(){
      ModelAndView mv = new ModelAndView("pages/formFuncionarioFuncao");
      mv.addObject("allSetors", setorService.findAll());
      return mv;
  }
  
  @PostMapping("/funcionario/funcao/create")
  public String saveFuncionarioFuncao(FuncionarioFuncao funcionarioFuncao, RedirectAttributes redirectAttributes){
    
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar a Função do funcionario");
    redirectAttributes.addAttribute("message_type","success");

    funcionarioFuncaoService.save(funcionarioFuncao); 
    

    return "redirect:/";

  }


}
