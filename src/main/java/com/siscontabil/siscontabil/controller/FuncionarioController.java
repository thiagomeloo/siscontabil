package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.DadosBancario;
import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.DadosBancarioServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioFuncaoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  DadosBancarioServiceImpl dadosBancarioService;

  @Autowired
  FuncionarioFuncaoServiceImpl funcionarioFuncaoService;

  @GetMapping("/funcionario/")
  public ModelAndView getFuncionariosFuncao(){
    ModelAndView mv = new ModelAndView("pages/listaFuncionario");
      mv.addObject("allFuncionario", funcionarioService.findAll());
      System.out.println("AAAAAAAAA"+mv.toString());
      return mv;
  }


  @GetMapping("/funcionario/create")
  public ModelAndView getFormCreate(){
    ModelAndView mv = new ModelAndView("pages/formFuncionario");
    mv.addObject("allFuncao", funcionarioFuncaoService.findAll() );
    return mv;
  } 

  @PostMapping({"/funcionario/create"})
  public String saveFuncionario( Funcionario funcionario,Endereco endereco, DadosBancario dadosBancario, 
   RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar o funcionario");
    redirectAttributes.addAttribute("message_type","success");
    
    Endereco e = enderecoService.save(endereco);
    DadosBancario d = dadosBancarioService.save(dadosBancario);
    
    funcionario.setDadosBancario(d);
    funcionario.setEndereco(e);
    
    funcionarioService.save(funcionario);
    
    return HOME_PAGE;
  }

  @GetMapping("/funcionario/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model){
    try {
      model.addAttribute("funcionario",funcionarioService.findById(id));
      model.addAttribute("allFuncao", funcionarioFuncaoService.findAll());
    } catch (Exception e) {
      return HOME_PAGE;
    }
    return "pages/formFuncionario";
  }

  @PostMapping({"/funcionario/update"})
  public String updateFuncionario(Funcionario funcionario,
    Endereco endereco, DadosBancario dadosBancario,
   RedirectAttributes redirectAttributes){
    
    enderecoService.save(endereco);
    dadosBancarioService.save(dadosBancario);

    funcionario.setEndereco(endereco);
    funcionario.setDadosBancario(dadosBancario);
    funcionarioService.save(funcionario); 


    redirectAttributes.addAttribute("message_text","Sucesso ao atualizar o funcionario");
    redirectAttributes.addAttribute("message_type","success");
    return HOME_PAGE;

  }
 
}



