package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Produto;
import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.ProdutoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController {

  private static final String HOME_PAGE = "redirect:/";

    @Autowired
    ProdutoServiceImpl produtoService;

    @Autowired
    FornecedorServiceImpl fornecedorService;

    @GetMapping("/produto/list")
  public ModelAndView getListProduto(){
    ModelAndView mv = new ModelAndView("pages/listProduto");
    mv.addObject("allProduto", produtoService.findAll());
    return mv;
  }

    @GetMapping("/produto/create")
    public ModelAndView getListFornecedor(){
      ModelAndView mv = new ModelAndView("pages/formProduto");
      mv.addObject("allFornecedor", fornecedorService.findAll());
      mv.addObject("allFornecedorAtivo", fornecedorService.allFornecedorAtivo());
      return mv;
    }

    @PostMapping({"/produto/create"})
    public String saveFuncionario( Produto produto,RedirectAttributes redirectAttributes){

      redirectAttributes.addAttribute("message_text","Sucesso ao cadastrar o produto");
      redirectAttributes.addAttribute("message_type","success");
  
      produtoService.save(produto);
      
      return HOME_PAGE;
    }

    @GetMapping("/produto/update/{id}")
    public String getFormUpdate(@PathVariable("id") long id, Model model){
      try {
        model.addAttribute("produto",produtoService.findById(id));
        model.addAttribute("allFornecedorAtivo", fornecedorService.allFornecedorAtivo());
      } catch (Exception e) {
        return HOME_PAGE;
      }
      return "pages/formProduto";
    }
    @PostMapping({"/produto/update"})
    public String updateProduto( Produto produto,RedirectAttributes redirectAttributes){
      redirectAttributes.addAttribute("message_text","Sucesso ao atualizar o produto");
      redirectAttributes.addAttribute("message_type","success");
      
      produtoService.save(produto);
    
      return HOME_PAGE;
    }
}
