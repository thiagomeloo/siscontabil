package com.siscontabil.siscontabil.controller;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Setor;
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
public class SetorController {

  private static final String HOME_PAGE = "redirect:/";
  @Autowired
  SetorServiceImpl setorService;

  Autentication auth = new Autentication();

  @GetMapping("/setor")
  public String getSetors(Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/listaSetor", auth.ADMIN);

    if (auth.isAutenticated(session)) {
      model.addAttribute("allSetors", setorService.findAll());
    }

    return url;
  }

  @GetMapping("/setor/create")
  public String getFormCreate(HttpSession session) {
    return (auth.getUrl(session, "pages/formSetor",auth.ADMIN));
  }

  @GetMapping("/setor/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/formSetor",auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        model.addAttribute("setor", setorService.findById(id));
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }

  @PostMapping("/setor/create")
  public String saveSetor(Setor setor, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE, auth.ADMIN);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao cadastrar o setor");
      redirectAttributes.addAttribute("message_type", "success");

      setorService.save(setor);
    }

    return url;
  }

  @PostMapping("/setor/update")
  public String updateSetor(Setor setor, RedirectAttributes redirectAttributes, HttpSession session) {

    String url = auth.getUrl(session, HOME_PAGE, auth.ADMIN);

    if (auth.isAutenticated(session)) {
      redirectAttributes.addAttribute("message_text", "Sucesso ao atualizar o setor");
      redirectAttributes.addAttribute("message_type", "success");
      setorService.save(setor);
    }

    return url;
  }

  @GetMapping("/setor/inativar/{id}")
  public String getInativo(@PathVariable("id") long id, Model model, HttpSession session,
  RedirectAttributes redirectAttributes) {

    String url = auth.getUrl(session, HOME_PAGE,auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        
        Setor setor = setorService.findById(id);
        setor.setStatus(false);
        setorService.save(setor);

        redirectAttributes.addAttribute("message_text", "Sucesso ao inativar o setor");
        redirectAttributes.addAttribute("message_type", "success");
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }
  @GetMapping("/setor/ativar/{id}")
  public String getAtivar(@PathVariable("id") long id, Model model, HttpSession session,
  RedirectAttributes redirectAttributes) {

    String url = auth.getUrl(session, HOME_PAGE,auth.ADMIN);

    if (auth.isAutenticated(session)) {
      try {
        
        Setor setor = setorService.findById(id);
        setor.setStatus(true);
        setorService.save(setor);

        redirectAttributes.addAttribute("message_text", "Sucesso ao ativar o setor");
        redirectAttributes.addAttribute("message_type", "success");
      } catch (Exception e) {
        return HOME_PAGE;
      }
    }

    return url;
  }
}
