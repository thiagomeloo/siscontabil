package com.siscontabil.siscontabil.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FolhaPagamentoController {
  
  @RequestMapping("/folha-pagamento/create")
    public String getIndex(){
        return "pages/formFolhaPagamento";
    }
}



