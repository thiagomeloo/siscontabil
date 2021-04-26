package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Cliente;
import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.service.serviceImplents.ClienteServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.EnderecoServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  ClienteServiceImpl clienteService;

  @Autowired
  EnderecoServiceImpl enderecoService;

  Autentication auth = new Autentication();

  @GetMapping("/cliente/list/")
  public String getCliente(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listCliente");
    if (auth.isAutenticated(session)) {
      model.addAttribute("allCliente", clienteService.findAll());
    }
    return url;

  }

  @GetMapping("/cliente/list/ativo")
  public String getClienteAtivo(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listCliente");
    if (auth.isAutenticated(session)) {
      model.addAttribute("allCliente", clienteService.allClienteAtivo());
    }
    return url;
  }

  @GetMapping("/cliente/list/inativo")
  public String getClienteInativo(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/listCliente");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allCliente", clienteService.allClienteInativo());
    }
    return url;
  }

  @GetMapping("/cliente/create/")
  public String getClienteCreate(HttpSession session, Model model) {

    String url = auth.getUrl(session, "pages/formCliente");

    if (auth.isAutenticated(session)) {
      model.addAttribute("allCliente", clienteService.findAll());
    }
    return url;

  }

  @PostMapping({ "/cliente/create" })
  public String saveCliente(Cliente cliente, Endereco endereco, RedirectAttributes redirectAttributes,
      HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {

      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o cliente");
      redirectAttributes.addAttribute("message_type", "success");

      Endereco e = enderecoService.save(endereco);

      cliente.setEndereco(e);

      clienteService.save(cliente);
    }

    return url;
  }

  @GetMapping("/cliente/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formCliente");

    if (auth.isAutenticated(session)) {
      try {
        model.addAttribute("cliente", clienteService.findById(id));
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping({ "/cliente/update" })
  public String updateCliente(Cliente cliente, Endereco endereco, RedirectAttributes redirectAttributes,
      HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE);

    if (auth.isAutenticated(session)) {

      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar o cliente");
      redirectAttributes.addAttribute("message_type", "success");

      if (cliente.isStatus() == false) {
        cliente.setStatus(cliente.isStatus());
      } else {
        cliente.setStatus(true);
      }
      Endereco e = enderecoService.save(endereco);
      cliente.setEndereco(e);
      clienteService.save(cliente);
    }

    return url;
  }

}
