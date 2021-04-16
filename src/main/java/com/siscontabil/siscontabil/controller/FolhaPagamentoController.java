package com.siscontabil.siscontabil.controller;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.ContraCheque;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class FolhaPagamentoController {
  
  @Autowired
  SetorServiceImpl setorService;

  @Autowired
  FuncionarioServiceImpl funcionarioService;

  @GetMapping("/folha-pagamento/create")
  public String getFormFolhaPagamento(HttpSession session, Model model){
    
    model.addAttribute("allSetor", setorService.findAll());

    if(session.getAttribute("contracheques") == null){
      model.addAttribute("allContraCheques", new ArrayList<ContraCheque>());
    }else{

      List<ContraCheque> contraCheques = contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      model.addAttribute("allContraCheques", contraCheques);
    }

    if(session.getAttribute("idSetor") != null){
      model.addAttribute("idSetor", session.getAttribute("idSetor"));
    }
    
    return "pages/formFolhaPagamento";
  }

  @GetMapping("/folha-pagamento/clear")
  public String clearFolhaPagamento(HttpSession session){
    session.removeAttribute("idSetor");
    session.removeAttribute("contracheques");
    return "redirect:/folha-pagamento/create";
  }

  @PostMapping("/folha-pagamento/save")
  public String saveFolhaPagamento(HttpSession session){
    //List<ContraCheque> contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
    //session.removeAttribute("idSetor");
    //session.removeAttribute("contracheques");
    return "redirect:/folha-pagamento/create";
  }

  @GetMapping("/folha-pagamento/contracheque/clear/{idFuncionario}")
  public String clearContraCheque(@PathVariable("idFuncionario") long idFuncionario, HttpSession session){
    List<ContraCheque> contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
    
    for (int i = 0; i < contraCheques.size(); i++) {
      ContraCheque contraCheque = contraCheques.get(i);
      if(contraCheque.getFuncionario().getId() == idFuncionario){
        contraCheques.remove(i);
        session.setAttribute("contracheques", contraCheques);
        break;
      } 
      
    }
    return "redirect:/folha-pagamento/create";
  }

  @GetMapping("/folha-pagamento/add-contracheque/{idSetor}")
  public String getViewAddContraCheque(@PathVariable("idSetor") long idSetor, HttpSession session, Model model){
    model.addAttribute("allFuncionarios", funcionarioService.allFuncionarioBySetor(idSetor));
    session.setAttribute("idSetor", idSetor);
    return "pages/formContraCheque";
  }
  
  @PostMapping("/folha-pagamento/add-contracheque")
  public String postAddContraCheque(HttpSession session, ContraCheque contraCheque, Model model){
    
    if(session.getAttribute("contracheques") == null){
      List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();
      contraCheques.add(contraCheque);
      session.setAttribute("contracheques", contraCheques);
    }else{
      List<ContraCheque> contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      contraCheques.add(contraCheque);
      session.setAttribute("contracheques", contraCheques);
    }

    return "redirect:/folha-pagamento/create";
  }
}



