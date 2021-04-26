package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.DadosBancario;
import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;
import com.siscontabil.siscontabil.service.serviceImplents.DadosBancarioServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioFuncaoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioController {

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  FuncionarioServiceImpl funcionarioService;

  @Autowired
  EnderecoServiceImpl enderecoService;

  @Autowired
  DadosBancarioServiceImpl dadosBancarioService;

  @Autowired
  FuncionarioFuncaoServiceImpl funcionarioFuncaoService;

  Autentication auth = new Autentication();

  @GetMapping("/funcionario/")
  public String getFuncionariosFuncao(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listaFuncionario");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFuncionario", funcionarioService.findAll());
    }

    return url;
  }

  @GetMapping("/funcionario/create")
  public String getFormCreate(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/formFuncionario");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFuncao", funcionarioFuncaoService.findAll());
    }

    return url;
  }

  @PostMapping({ "/funcionario/create" })
  public String saveFuncionario(Funcionario funcionario, Endereco endereco, DadosBancario dadosBancario,
      RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o funcionario");
      redirectAttributes.addAttribute("message_type", "success");

      Endereco e = enderecoService.save(endereco);
      DadosBancario d = dadosBancarioService.save(dadosBancario);

      funcionario.setDadosBancario(d);
      funcionario.setEndereco(e);

      funcionarioService.save(funcionario);
    }

    return url;
  }

  @GetMapping("/funcionario/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formFuncionario");

    if (auth.isAutenticated(session)) {
      try {
        model.addAttribute("funcionario", funcionarioService.findById(id));
        model.addAttribute("allFuncao", funcionarioFuncaoService.findAll());
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping({ "/funcionario/update" })
  public String updateFuncionario(Funcionario funcionario, Endereco endereco, DadosBancario dadosBancario,
      RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {
      enderecoService.save(endereco);
      dadosBancarioService.save(dadosBancario);

      funcionario.setEndereco(endereco);
      funcionario.setDadosBancario(dadosBancario);
      funcionarioService.save(funcionario);

      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar o funcionario");
      redirectAttributes.addAttribute("message_type", "success");

    }

    return url;

  }

}
