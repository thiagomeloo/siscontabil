package com.siscontabil.siscontabil.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Movimentacao;
import com.siscontabil.siscontabil.model.Produto;
import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.MovimentacaoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.ProdutoServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

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

  @Autowired
  MovimentacaoServiceImpl movimentacaoService;

  Autentication auth = new Autentication();

  @GetMapping("/produto/list")
  public String getListProduto(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listProduto");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allProduto", produtoService.findAll());

    }

    return url;

  }

  @GetMapping("/produto/create")
  public String getListFornecedor(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/formProduto");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFornecedorAtivo", fornecedorService.allFornecedorAtivo());

    }

    return url;
  }

  @PostMapping({ "/produto/create" })
  public String saveFuncionario(Produto produto, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o produto");
      redirectAttributes.addAttribute("message_type", "success");

      produtoService.save(produto);

      Movimentacao movimentacao = new Movimentacao();
      movimentacao.setTipo("Saida");
      movimentacao.setDescricao("| cadastro | Id produto: " + produto.getId() + " | Nome: " + produto.getDescricao());
      movimentacao.setValor(produto.getValorDeCusto() * produto.getQuantidade());
      movimentacao.setDataMovimentacao(new Date());
      movimentacaoService.save(movimentacao);
    }

    return url;
  }

  @GetMapping("/produto/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formProduto");

    if (auth.isAutenticated(session)) {

      try {
        model.addAttribute("produto", produtoService.findById(id));
        model.addAttribute("allFornecedorAtivo", fornecedorService.allFornecedorAtivo());
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping({ "/produto/update" })
  public String updateProduto(Produto produto, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar o produto");
      redirectAttributes.addAttribute("message_type", "success");

      Produto produtoOld = produtoService.findById(produto.getId());
      double oldProduto = produtoOld.getValorDeCusto();

      produtoService.save(produto);

      Movimentacao movimentacao = new Movimentacao();

      double valor = 0;

      double newProduto = produto.getValorDeCusto();

      if (newProduto > oldProduto) {
        valor = (newProduto - oldProduto) * produto.getQuantidade();
        movimentacao.setTipo("Saida");
      } else {
        movimentacao.setTipo("Entrada");
        valor = (oldProduto - newProduto) * produto.getQuantidade();
      }

      movimentacao.setDescricao("| update | Id produto: " + produto.getId() + " | Nome: " + produto.getDescricao());
      movimentacao.setValor(valor);
      movimentacao.setDataMovimentacao(new Date());
      movimentacaoService.save(movimentacao);
    }

    return url;
  }
}
