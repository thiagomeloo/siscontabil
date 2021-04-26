package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.FuncionarioFuncao;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioFuncaoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioFuncaoController {

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  FuncionarioFuncaoServiceImpl funcionarioFuncaoService;

  @Autowired
  SetorServiceImpl setorService;

  Autentication auth = new Autentication();

  @GetMapping("/funcionario/funcao")
  public String getFuncionariosFuncao(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listaFuncionarioFuncao",auth.ADMIN);

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFuncoes", funcionarioFuncaoService.findAll());

    }
    return url;
  }

  @GetMapping("/funcionario/funcao/create")
  public String getFormCreate(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/formFuncionarioFuncao",auth.ADMIN);

    if (auth.isAutenticated(session)) {
      model.addAttribute("allSetors", setorService.findAll());
    }

    return url;
  }

  @GetMapping("/funcionario/funcao/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formFuncionarioFuncao",auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        model.addAttribute("funcao", funcionarioFuncaoService.findById(id));
        model.addAttribute("allSetors", setorService.findAll());
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping({ "/funcionario/funcao/create" })
  public String saveFuncionarioFuncao(FuncionarioFuncao funcionarioFuncao, RedirectAttributes redirectAttributes,
      HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE,auth.ADMIN);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar a Função do funcionario");
      redirectAttributes.addAttribute("message_type", "success");

      funcionarioFuncaoService.save(funcionarioFuncao);

    }

    return url;

  }

  @PostMapping({ "/funcionario/funcao/update" })
  public String updateFuncionarioFuncao(FuncionarioFuncao funcionarioFuncao, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE,auth.ADMIN);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar a Função do funcionario");
      redirectAttributes.addAttribute("message_type", "success");

      funcionarioFuncaoService.save(funcionarioFuncao);
    }

    return url;

  }

}
