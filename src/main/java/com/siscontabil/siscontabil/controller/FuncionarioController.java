package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioController {

  private static final String HOME_PAGE = "redirect:/";
  
  @Autowired
  FuncionarioServiceImpl funcionarioService;

  @Autowired
  EnderecoServiceImpl enderecoService;

  @GetMapping("/funcionario/create")
  public ModelAndView getFormCreate(){
    ModelAndView mv = new ModelAndView("pages/formFuncionario");
    mv.addObject("allEndereco", enderecoService.findAll());
    mv.addObject("allFuncinarios", funcionarioService.findAll());
    return mv;
  }

  @PostMapping({"/funcionario/create"})
  public String saveFuncionario( Funcionario funcionario,Endereco endereco, RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar o funcionario");
    redirectAttributes.addAttribute("message_type","success");
    Endereco e = enderecoService.save(endereco);
    funcionario.setEndereco(e);
    //enderecoService.save(endereco);
    funcionarioService.save(funcionario);
    
    return HOME_PAGE;
  }

 
}



