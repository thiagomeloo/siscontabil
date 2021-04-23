package com.siscontabil.siscontabil.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.siscontabil.siscontabil.model.ContraCheque;
import com.siscontabil.siscontabil.model.FolhaPagamento;
import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.service.serviceImplents.ContraChequeServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FolhaPagamentoServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.FuncionarioServiceImpl;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FolhaPagamentoController {

  private static final String HOME_PAGE = "redirect:/";

  @Autowired
  SetorServiceImpl setorService;

  @Autowired
  FuncionarioServiceImpl funcionarioService;

  @Autowired
  FolhaPagamentoServiceImpl folhaPagamentoService;

  @Autowired
  ContraChequeServiceImpl contraChequeService;

  @GetMapping("/folha-pagamento/")
  public ModelAndView getFolhasPagamento() {
    ModelAndView mv = new ModelAndView("pages/listaFolhaPagamento");
    mv.addObject("allFolhasPagamentos", folhaPagamentoService.findAll());
    return mv;
  }

  @GetMapping("/folha-pagamento/details/{idFolhaPagamento}")
  public String detailFolhaPagamento(@PathVariable("idFolhaPagamento") long idFolhaPagamento, Model model) {
    FolhaPagamento folhaPagamento = folhaPagamentoService.findById(idFolhaPagamento);
    model.addAttribute("folhaPagamento", folhaPagamento);

    double totalProventos = 0;
    double totalComissao = 0;
    double totalDescontoINSS = 0;

    List<ContraCheque> contraCheques = folhaPagamento.getContraCheques();
    for (int i = 0; i < contraCheques.size(); i++) {
      totalProventos += contraCheques.get(i).getFuncionario().getFuncao().getSalario();
      totalComissao += contraCheques.get(i).getComissao();
      totalDescontoINSS += contraCheques.get(i).getDescontoINSS();
    }

    model.addAttribute("totalProventos", totalProventos);
    model.addAttribute("totalComissao", totalComissao);
    model.addAttribute("totalDescontoINSS", totalDescontoINSS);
    return "pages/detailsFolhaPagamento";
  }

  @GetMapping("/folha-pagamento/create")
  public String getFormFolhaPagamento(HttpSession session, Model model) {

    model.addAttribute("allSetor", setorService.findAll());

    if (session.getAttribute("contracheques") == null) {
      model.addAttribute("allContraCheques", new ArrayList<ContraCheque>());
    } else {

      List<ContraCheque> contraCheques = contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      model.addAttribute("allContraCheques", contraCheques);
    }

    if (session.getAttribute("idSetor") != null) {
      model.addAttribute("idSetor", session.getAttribute("idSetor"));
    }

    return "pages/formFolhaPagamento";
  }

  @GetMapping("/folha-pagamento/update/{idFolhaPagamento}")
  public String getFormUpdateFolhaPagamento(@PathVariable("idFolhaPagamento") long idFolhaPagamento,
      HttpSession session, Model model) {

    model.addAttribute("allSetor", setorService.findAll());
    FolhaPagamento folhaPagamento = folhaPagamentoService.findById(idFolhaPagamento);

    model.addAttribute("folhaPagamento", folhaPagamento);

    if (session.getAttribute("contrachequesUpdate") != null) {
      model.addAttribute("allContraCheques", session.getAttribute("contrachequesUpdate"));
    } else {
      model.addAttribute("allContraCheques", folhaPagamento.getContraCheques());
      session.setAttribute("contrachequesUpdate", folhaPagamento.getContraCheques());
    }

    model.addAttribute("idSetor", folhaPagamento.getSetor().getId());

    session.setAttribute("folhaPagamento", folhaPagamento);

    return "pages/formFolhaPagamento";
  }

  @GetMapping("/folha-pagamento/clear")
  public String clearFolhaPagamento(HttpSession session) {
    session.removeAttribute("idSetor");
    session.removeAttribute("contracheques");
    session.removeAttribute("contrachequesUpdate");
    session.removeAttribute("contrachequesDelete");
    return "redirect:/folha-pagamento/create";
  }

  @PostMapping("/folha-pagamento/save")
  public String saveFolhaPagamento(HttpSession session, RedirectAttributes redirectAttributes) {

    List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();
    FolhaPagamento fPagamento = new FolhaPagamento();

    if(session.getAttribute("idSetor") == null || session.getAttribute("contracheques") == null){
      redirectAttributes.addAttribute("message_text","Impossivel salvar uma folha de pagamento vazia!");
      redirectAttributes.addAttribute("message_type","danger");
      return "redirect:/folha-pagamento/create";
    }

    if (session.getAttribute("folhaPagamento") != null) {
      fPagamento = (FolhaPagamento) session.getAttribute("folhaPagamento");
      contraCheques = (List<ContraCheque>) session.getAttribute("contrachequesUpdate");
      fPagamento.setContraCheques(contraCheques);
      session.removeAttribute("contrachequesUpdate");
    } else {
      contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      fPagamento.setContraCheques(contraCheques);
      fPagamento.setCompetencia(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/yyyy")));
      fPagamento.setSetor(setorService.findById((Long) session.getAttribute("idSetor")));
      session.removeAttribute("contracheques");
    }

    folhaPagamentoService.save(fPagamento);

    session.removeAttribute("idSetor");

    if (session.getAttribute("contrachequesDelete") != null) {
     List<ContraCheque> contraChequesDelete = (List<ContraCheque>) session.getAttribute("contrachequesDelete");
     for(int i = 0; i < contraChequesDelete.size(); i++){
       contraChequeService.deleteById(contraChequesDelete.get(i).getId());
     } 
    }

    return HOME_PAGE;
  }

  @GetMapping("/folha-pagamento/contracheque/clear/{idFuncionario}")
  public String clearContraCheque(@PathVariable("idFuncionario") long idFuncionario, HttpSession session) {

    List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();

    if (session.getAttribute("folhaPagamento") != null) {
      FolhaPagamento folhaPagamento = (FolhaPagamento) session.getAttribute("folhaPagamento");
      contraCheques = (List<ContraCheque>) session.getAttribute("contrachequesUpdate");
      for (int i = 0; i < contraCheques.size(); i++) {
        ContraCheque contraCheque = contraCheques.get(i);
        if (contraCheque.getFuncionario().getId() == idFuncionario) {

          List<ContraCheque> contraChequesDelete = new ArrayList<ContraCheque>();
          if (session.getAttribute("contrachequesDelete") == null) {
            contraChequesDelete.add(contraCheque);
            session.setAttribute("contrachequesDelete", contraChequesDelete);
          }else{
            contraChequesDelete = (List<ContraCheque>) session.getAttribute("contrachequesDelete");
            contraChequesDelete.add(contraCheque);
            session.setAttribute("contrachequesDelete", contraChequesDelete);
          }

          contraCheques.remove(i);
          session.setAttribute("contrachequesUpdate", contraCheques);
          break;
        }
      }

      return "redirect:/folha-pagamento/update/" + folhaPagamento.getId();

    } else {

      contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");

      for (int i = 0; i < contraCheques.size(); i++) {
        ContraCheque contraCheque = contraCheques.get(i);
        if (contraCheque.getFuncionario().getId() == idFuncionario) {
          contraCheques.remove(i);
          session.setAttribute("contracheques", contraCheques);
          break;
        }

      }
      return "redirect:/folha-pagamento/create";
    }

  }

  @GetMapping("/folha-pagamento/add-contracheque/{idSetor}")
  public String getViewAddContraCheque(@PathVariable("idSetor") long idSetor, HttpSession session, Model model) {

    List<ContraCheque> contraChequesInclusos = new ArrayList<ContraCheque>();
    if (session.getAttribute("contracheques") != null) {
      contraChequesInclusos = (List<ContraCheque>) session.getAttribute("contracheques");
    } else if (session.getAttribute("contrachequesUpdate") != null) {
      contraChequesInclusos = (List<ContraCheque>) session.getAttribute("contrachequesUpdate");
    }

    model.addAttribute("funcSalario", funcionarioService.allFuncionarioAndSalarioBySetor(idSetor));

    List<Funcionario> funcionarios = funcionarioService.allFuncionarioBySetor(idSetor);

    for (int i = 0; i < contraChequesInclusos.size(); i++) {
      for (int j = 0; j < funcionarios.size(); j++) {
        if (contraChequesInclusos.get(i).getFuncionario().getId() == funcionarios.get(j).getId()) {
          funcionarios.remove(j);
        }
      }

    }

    model.addAttribute("allFuncionarios", funcionarios);
    session.setAttribute("idSetor", idSetor);
    return "pages/formContraCheque";
  }

  @PostMapping("/folha-pagamento/add-contracheque")
  public String postAddContraCheque(HttpSession session, ContraCheque contraCheque, Model model) {

    if (session.getAttribute("contracheques") == null) {
      List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();
      contraCheques.add(contraCheque);
      session.setAttribute("contracheques", contraCheques);
    } else {
      List<ContraCheque> contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");
      contraCheques.add(contraCheque);
      session.setAttribute("contracheques", contraCheques);
    }

    return "redirect:/folha-pagamento/create";
  }
}
