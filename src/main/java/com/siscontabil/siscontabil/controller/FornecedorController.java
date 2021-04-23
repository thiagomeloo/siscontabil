package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Fornecedor;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FornecedorController {

  private static final String HOME_PAGE = "redirect:/";
  
  
  @Autowired
  FornecedorServiceImpl fornecedorService;

  @Autowired
  EnderecoServiceImpl enderecoService;

  @GetMapping("/fornecedor/list")
  public ModelAndView getListFornecedor(){
    ModelAndView mv = new ModelAndView("pages/listFornecedor");
    mv.addObject("allFornecedor", fornecedorService.findAll());
    return mv;
  }

  @GetMapping("/fornecedor/create")
  public ModelAndView getFormFornecedor(){
    ModelAndView mv = new ModelAndView("pages/formFornecedor");
    mv.addObject("allFornecedor", fornecedorService.findAll());
    return mv;
  }

  @PostMapping({"/fornecedor/create"})
  public String saveFuncionario( Fornecedor fornecedor,Endereco endereco, 
   RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar o fornecedor");
    redirectAttributes.addAttribute("message_type","success");
    
    Endereco e = enderecoService.save(endereco);
    fornecedor.setEndereco(e);
    fornecedorService.save(fornecedor);
  
    return HOME_PAGE;
  }

  @GetMapping("/fornecedor/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model){
    try {
      model.addAttribute("fornecedor",fornecedorService.findById(id));
    } catch (Exception e) {
      return HOME_PAGE;
    }
    return "pages/formFornecedor";
  }

  @PostMapping({"/fornecedor/update"})
  public String updateFuncionario( Fornecedor fornecedor,Endereco endereco, 
   RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao atualizar o fornecedor");
    redirectAttributes.addAttribute("message_type","success");
    
    if(fornecedor.isStatus() == false){
      fornecedor.setStatus(fornecedor.isStatus());
    }else{
      fornecedor.setStatus(true);
    }
    Endereco e = enderecoService.save(endereco);
    fornecedor.setEndereco(e);
    fornecedorService.save(fornecedor);
  
    return HOME_PAGE;
  }

}



