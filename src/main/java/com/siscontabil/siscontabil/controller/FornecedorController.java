package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Fornecedor;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;
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
public class FornecedorController {

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  FornecedorServiceImpl fornecedorService;

  @Autowired
  EnderecoServiceImpl enderecoService;

  Autentication auth = new Autentication();

  @GetMapping("/fornecedor/list")
  public String getListFornecedor(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listFornecedor");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFornecedor", fornecedorService.findAll());
    }

    return url;
  }

  @GetMapping("/fornecedor/create")
  public String getFormFornecedor(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/formFornecedor");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allFornecedor", fornecedorService.findAll());
    }

    return url;
  }

  @PostMapping({ "/fornecedor/create" })
  public String saveFuncionario(Fornecedor fornecedor, Endereco endereco, RedirectAttributes redirectAttributes,
      HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o fornecedor");
      redirectAttributes.addAttribute("message_type", "success");

      Endereco e = enderecoService.save(endereco);
      fornecedor.setEndereco(e);
      fornecedorService.save(fornecedor);
    }

    return url;
  }

  @GetMapping("/fornecedor/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formFornecedor");

    if (auth.isAutenticated(session)) {
      try {
        model.addAttribute("fornecedor", fornecedorService.findById(id));
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping({ "/fornecedor/update" })
  public String updateFuncionario(Fornecedor fornecedor, Endereco endereco, RedirectAttributes redirectAttributes,
      HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {

      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar o fornecedor");
      redirectAttributes.addAttribute("message_type", "success");

      if (fornecedor.isStatus() == false) {
        fornecedor.setStatus(fornecedor.isStatus());
      } else {
        fornecedor.setStatus(true);
      }
      Endereco e = enderecoService.save(endereco);
      fornecedor.setEndereco(e);
      fornecedorService.save(fornecedor);
      
    }

    return url;
  }

}
