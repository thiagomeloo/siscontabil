package com.siscontabil.siscontabil.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.Movimentacao;
import com.siscontabil.siscontabil.service.serviceImplents.MovimentacaoServiceImpl;
import com.siscontabil.siscontabil.util.Autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

  @Autowired
  MovimentacaoServiceImpl movimentacaoService;

  Autentication auth = new Autentication();

  @GetMapping("/report/mensal")
  public String getReport(Model model, HttpSession session) {

    String url = auth.getUrl(session, "pages/reportLançamento");

    if (auth.isAutenticated(session)) {
      List<Movimentacao> m = movimentacaoService.findAll();
      double valorTotalEntrada = 0;
      double valorTotalSaida = 0;
      for (int i = 0; i < m.size(); i++) {
        if (m.get(i).getTipo().equals("Entrada")) {
          valorTotalEntrada += m.get(i).getValor();
        } else {
          valorTotalSaida += m.get(i).getValor();
        }

      }
      model.addAttribute("valorTotalEntrada", valorTotalEntrada);
      model.addAttribute("valorTotalSaida", valorTotalSaida);
      model.addAttribute("allReport", m);
    }

    return url;
  }

}
