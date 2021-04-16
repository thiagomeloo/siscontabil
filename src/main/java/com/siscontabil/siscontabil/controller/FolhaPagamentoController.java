package com.siscontabil.siscontabil.controller;


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
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class FolhaPagamentoController {
  
  @Autowired
  SetorServiceImpl setorService;

  @Autowired
  FuncionarioServiceImpl funcionarioService;

  @GetMapping("/folha-pagamento/create")
  public String getFormFolhaPagamento(HttpSession session, Model model){
    if(session.getAttribute("contracheques") == null){

      model.addAttribute("allContraCheques", new ArrayList<ContraCheque>());
      model.addAttribute("allSetor", setorService.findAll());
    }else{

      List<ContraCheque> contraCheques = contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      model.addAttribute("allContraCheques", contraCheques);
    }

    return "pages/formFolhaPagamento";
  }

  @GetMapping("/folha-pagamento/add-contracheque")
  public String getViewAddContraCheque(HttpSession session, Model model){
    model.addAttribute("allFuncionarios", funcionarioService.findAll());
    return "pages/formContraCheque";
  }
  
  @PostMapping("/folha-pagamento/add-contracheque")
  public String postAddContraCheque(HttpSession session, ContraCheque contraCheque){
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



