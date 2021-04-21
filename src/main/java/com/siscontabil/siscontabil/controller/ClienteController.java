package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Cliente;
import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.service.serviceImplents.ClienteServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

private static final String HOME_PAGE = "redirect:/";

@Autowired
ClienteServiceImpl clienteService;

@Autowired
EnderecoServiceImpl enderecoService;

@GetMapping("/cliente/list/")
public ModelAndView getCliente(){
  ModelAndView mv = new ModelAndView("pages/listCliente");
    mv.addObject("allCliente", clienteService.findAll());
    return mv;
}

@GetMapping("/cliente/list/ativo")
public ModelAndView getClienteAtivo(){
  ModelAndView mv = new ModelAndView("pages/listCliente");
    mv.addObject("allCliente", clienteService.allClienteAtivo());
    return mv;
}

@GetMapping("/cliente/list/inativo")
public ModelAndView getClienteInativo(){
  ModelAndView mv = new ModelAndView("pages/listCliente");
    mv.addObject("allCliente", clienteService.allClienteInativo());
    return mv;
}

@GetMapping("/cliente/create/")
public ModelAndView getClienteCreate(){
  ModelAndView mv = new ModelAndView("pages/formCliente");
    mv.addObject("allCliente", clienteService.findAll());
    return mv;
}

@PostMapping({"/cliente/create"})
  public String saveCliente( Cliente cliente,Endereco endereco, 
   RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar o cliente");
    redirectAttributes.addAttribute("message_type","success");
    
    Endereco e = enderecoService.save(endereco);
    
    cliente.setEndereco(e);
    
    clienteService.save(cliente);
    
    return HOME_PAGE;
  }

  @GetMapping("/cliente/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model){
    try {
      model.addAttribute("cliente",clienteService.findById(id));
    } catch (Exception e) {
      return HOME_PAGE;
    }
    return "pages/formCliente";
  }

  @PostMapping({"/cliente/update"})
  public String updateCliente( Cliente cliente,Endereco endereco, 
   RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("message_text","Sucesso ao atualizar o cliente");
    redirectAttributes.addAttribute("message_type","success");
    
    if(cliente.isStatus() == false){
      cliente.setStatus(cliente.isStatus());
    }else{
      cliente.setStatus(true);
    }
    Endereco e = enderecoService.save(endereco);
    cliente.setEndereco(e);
    clienteService.save(cliente);
  
    return HOME_PAGE;
  }

}
