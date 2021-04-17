package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.ProdutoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoServiceImpl produtoService;

    @Autowired
    FornecedorServiceImpl fornecedorService;

    @GetMapping("/produto/create")
    public ModelAndView getListFornecedor(){
      ModelAndView mv = new ModelAndView("pages/formProduto");
      mv.addObject("allFornecedor", fornecedorService.findAll());
      return mv;
    }
}
