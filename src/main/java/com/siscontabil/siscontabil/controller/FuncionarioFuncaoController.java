package com.siscontabil.siscontabil.controller;


import com.siscontabil.siscontabil.model.FuncionarioFuncao;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioFuncaoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioFuncaoController {
  

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  FuncionarioFuncaoServiceImpl funcionarioFuncaoService;

  @Autowired
  SetorServiceImpl setorService;
  

  @GetMapping("/funcionario/funcao")
  public ModelAndView getFuncionariosFuncao(){
    ModelAndView mv = new ModelAndView("pages/listaFuncionarioFuncao");
      mv.addObject("allFuncoes", funcionarioFuncaoService.findAll());
      return mv;
  }

  @GetMapping("/funcionario/funcao/create")
  public ModelAndView getFormCreate(){
      ModelAndView mv = new ModelAndView("pages/formFuncionarioFuncao");
      mv.addObject("allSetors", setorService.findAll());
      return mv;
  }

  @GetMapping("/funcionario/funcao/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model){
    
    try {
      model.addAttribute("funcao", funcionarioFuncaoService.findById(id));
      model.addAttribute("allSetors",setorService.findAll());
    } catch (Exception e) {
      return HOME_PAGE;
    }
    
    return "pages/formFuncionarioFuncao";
  }
  
  @PostMapping({"/funcionario/funcao/create"})
  public String saveFuncionarioFuncao(FuncionarioFuncao funcionarioFuncao, RedirectAttributes redirectAttributes){
    
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar a Função do funcionario");
    redirectAttributes.addAttribute("message_type","success");

    funcionarioFuncaoService.save(funcionarioFuncao); 
    

    return HOME_PAGE;

  }

  @PostMapping({"/funcionario/funcao/update"})
  public String updateFuncionarioFuncao(FuncionarioFuncao funcionarioFuncao, RedirectAttributes redirectAttributes){
    
    redirectAttributes.addAttribute("message_text","Sucesso ao atualizar a Função do funcionario");
    redirectAttributes.addAttribute("message_type","success");

    funcionarioFuncaoService.save(funcionarioFuncao); 
    

    return HOME_PAGE;

  }


}
