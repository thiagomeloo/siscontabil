package com.siscontabil.siscontabil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FornecedorController {
  
  @RequestMapping("/fornecedor/create")
  public String getFormFornecedor(){
    return "pages/formFornecedor";
  }
}



