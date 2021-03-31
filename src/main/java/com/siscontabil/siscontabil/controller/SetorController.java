package com.siscontabil.siscontabil.controller;

import com.siscontabil.siscontabil.model.Setor;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SetorController {
  

  private static final String HOME_PAGE = "redirect:/";
  @Autowired
  SetorServiceImpl setorService;

  @GetMapping("/setor")
  public ModelAndView getSetors(){
    ModelAndView mv = new ModelAndView("pages/listaSetor");
      mv.addObject("allSetors", setorService.findAll());
      return mv;
  }

  @GetMapping("/setor/create")
  public String getFormCreate(){
      return "pages/formSetor";
  }

  @GetMapping("/setor/update/{id}")
  public String getFormUpdate(@PathVariable("id") long id, Model model){
    try {
      model.addAttribute("setor",setorService.findById(id));
    } catch (Exception e) {
      return HOME_PAGE;
    }
    return "pages/formSetor";
  }
  
  @PostMapping("/setor/create")
  public String saveSetor(Setor setor){
      setorService.save(setor); 
      return HOME_PAGE;
  }

  @PostMapping("/setor/update")
  public String updateSetor(Setor setor){
      setorService.save(setor); 
      return HOME_PAGE;
  }


}
