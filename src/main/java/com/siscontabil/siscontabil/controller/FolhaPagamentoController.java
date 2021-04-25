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
import com.siscontabil.siscontabil.util.FolhaPagamentoAnual;

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
  /*
   * exibir relatório de folhas mensais.
   * 
   * @return view com todas as folhas de todos os meses.
   * 
   */
  @GetMapping("/folha-pagamento/report/")
  public String reportFolhaPagamento( Model model) {
    
    List<FolhaPagamento> folhaPagamento = folhaPagamentoService.findAll();
    model.addAttribute("folhaPagamento", folhaPagamento);
    
    double valorTotal =0;
    double ValorTotalPorFolha = 0;
    int quantEmpregadoTotal =0;

    for(int i =0; i<folhaPagamento.size();i++){
      valorTotal += folhaPagamento.get(i).getValorTotal();
      quantEmpregadoTotal += folhaPagamento.get(i).getQuantEmpregado();
    }
    
    model.addAttribute("ValorTotalPorFolha", ValorTotalPorFolha);
    model.addAttribute("valorTotal", valorTotal);
    model.addAttribute("quantEmpregadoTotal", quantEmpregadoTotal);
    return "pages/reportFolhaPagamento";
  }
/*
   * exibir relatório de folhas mensais.
   * 
   * @return view com toodas as folhas de um  mes.
   * 
   */
  @PostMapping("/folha-pagamento/report/")
  public String reportFolhaPagamentoMes( Model model, FolhaPagamento f) {
    String mes= f.getCompetencia().substring(6,7);
    String ano = f.getCompetencia().substring(0, 4);
    f.setCompetencia(mes+"/"+ano);
    List<FolhaPagamento> folhaPagamento = folhaPagamentoService.allFolhaPagamentoMes(mes+"/"+ano);
    model.addAttribute("folhaPagamento", folhaPagamento);
    model.addAttribute("folha", f);
    double valorTotal =0;
    double ValorTotalPorFolha = 0;
    int quantEmpregadoTotal =0;

    for(int i =0; i<folhaPagamento.size();i++){
      valorTotal += folhaPagamento.get(i).getValorTotal();
      quantEmpregadoTotal += folhaPagamento.get(i).getQuantEmpregado();
    }
    
    model.addAttribute("ValorTotalPorFolha", ValorTotalPorFolha);
    model.addAttribute("valorTotal", valorTotal);
    model.addAttribute("quantEmpregadoTotal", quantEmpregadoTotal);
    return "pages/reportFolhaPagamento";
  }
  
  @GetMapping("/folha-pagamento/report/anual")
  public String reportFolhaPagamentoAnual(Model model) {
    
    FolhaPagamentoAnual folhaAnual = new FolhaPagamentoAnual(folhaPagamentoService.findAll());

    model.addAttribute("folhaAnual", folhaAnual);
    model.addAttribute("valorTotal", folhaAnual.getValorTotal());
    model.addAttribute("quantEmpregadoTotal", folhaAnual.getQuantEmpregadoTotal());
    return "pages/reportFolhaPagamentoAnual";
  }

  @PostMapping("/folha-pagamento/report/anual")
  public String reportFolhaPagamentoAno(Model model, FolhaPagamento f) {
    
    FolhaPagamentoAnual folhaAnual = new FolhaPagamentoAnual(folhaPagamentoService.allFolhaPagamentoCompetencia(f.getCompetencia()));

    model.addAttribute("folhaAnual", folhaAnual);
    model.addAttribute("valorTotal", folhaAnual.getValorTotal());
    model.addAttribute("quantEmpregadoTotal", folhaAnual.getQuantEmpregadoTotal());

    return "pages/reportFolhaPagamentoAnual";
  }

  /*
   * Lista todas as folhas de pagamento.
   * 
   * @return view com a lista de todas as folhas de pagamento.
   * 
   */

  @GetMapping("/folha-pagamento/")
  public ModelAndView getFolhasPagamento() {
    ModelAndView mv = new ModelAndView("pages/listaFolhaPagamento");
    mv.addObject("allFolhasPagamentos", folhaPagamentoService.findAll());
    return mv;
  }
  /*
   * Exibir detalhes da folha de pagamento.
   * 
   * @param idFolhaPagamento id da folha de pagamento a ser detalhada.
   * 
   * @return view com detalhes da folha de pagamento.
   * 
   */
  @GetMapping("/folha-pagamento/details/{idFolhaPagamento}")
  public String detailFolhaPagamento(@PathVariable("idFolhaPagamento") long idFolhaPagamento, Model model) {
    FolhaPagamento folhaPagamento = folhaPagamentoService.findById(idFolhaPagamento);
    model.addAttribute("folhaPagamento", folhaPagamento);

    double totalProventos = 0;
    double totalComissao = 0;
    double totalDescontoINSS = 0;
    double valorTotal = 0;

    List<ContraCheque> contraCheques = folhaPagamento.getContraCheques();
    for (int i = 0; i < contraCheques.size(); i++) {
      totalProventos += contraCheques.get(i).getFuncionario().getFuncao().getSalario();
      totalComissao += contraCheques.get(i).getComissao();
      totalDescontoINSS += contraCheques.get(i).getDescontoDinheiro();
      valorTotal += contraCheques.get(i).getSalarioLiquido();
    }

    model.addAttribute("totalProventos", totalProventos);
    model.addAttribute("totalComissao", totalComissao);
    model.addAttribute("totalDescontoINSS", totalDescontoINSS);
    model.addAttribute("valorTotal", valorTotal);
    return "pages/detailsFolhaPagamento";
  }

  /*
   * Exibir formulario de criacao de folha de pagamento
   * 
   * @return view com o formulário de criação de folha de pagamento.
   * 
   */
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

  /*
   * Exibir formulario de atualização de folha de pagamento.
   * 
   * @return view com o formulário de atualização de folha de pagamento.
   * 
   */
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

  /*
   * Limpa a sessão.
   * 
   * @param HttpSession sessao a ser limpada
   * 
   */
  public void clearSession(HttpSession session) {
    session.removeAttribute("idSetor");
    session.removeAttribute("contracheques");
    session.removeAttribute("contrachequesUpdate");
    session.removeAttribute("contrachequesDelete");
    session.removeAttribute("folhaPagamento");
  }

  /*
   * Limpa a sessão com os dados de folha de pagamento.
   * 
   * @return view com o formulário de criação de folha de pagamento.
   * 
   */
  @GetMapping("/folha-pagamento/clear")
  public String clearFolhaPagamento(HttpSession session) {
    clearSession(session);
    return "redirect:/folha-pagamento/create";
  }

  /*
   * Persiste a folha de pagamento no banco de dados.
   * 
   * @return view HomePage em caso de sucesso.
   * 
   * @return view create folha de pagamento se caso a lista de contracheques ou o
   * id do setor estiverem vazios .
   * 
   */
  @PostMapping("/folha-pagamento/save")
  public String saveFolhaPagamento(HttpSession session, RedirectAttributes redirectAttributes) {

    List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();
    FolhaPagamento fPagamento = new FolhaPagamento();

    if (session.getAttribute("idSetor") == null && session.getAttribute("folhaPagamento") == null) {
      redirectAttributes.addAttribute("message_text", "Impossivel salvar uma folha de pagamento vazia!");
      redirectAttributes.addAttribute("message_type", "danger");
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

    clearSession(session);

    if (session.getAttribute("contrachequesDelete") != null) {
      List<ContraCheque> contraChequesDelete = (List<ContraCheque>) session.getAttribute("contrachequesDelete");
      for (int i = 0; i < contraChequesDelete.size(); i++) {
        contraChequeService.deleteById(contraChequesDelete.get(i).getId());
      }
    }

    return HOME_PAGE;
  }

  /*
   * Remove um contraCheque da lista de contraCheques da sessão.
   * 
   * @param idFuncionario id do funcionario a ser removido da lista de
   * contracheques da sessão.
   * 
   * @return view com o formulário de criação de folha de pagamento caso o
   * processo de criação da folha esteja em andamento.
   * 
   * @return view com o formulário de atualização de folha de pagamento caso o
   * processo de atualização da folha esteja em andamento.
   * 
   */
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
          } else {
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

  /*
   * Exibe o formulario de criação de contraCheque a ser adicionado a folha de
   * pagamento.
   * 
   * @param idSetor, id do setor escolhido para geração da folha.
   * 
   * @return view formulario de criação de contraCheque listando pelo setor os
   * funcionarios a serem escolhidos.
   * 
   */
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

  /*
   * Adiciona na sessão o contraCheque criado.
   * 
   * @param ContraCheque, contraCheque a ser adicionado na sessão.
   * 
   * @return view formulario de criação de folha de pagamentos.
   * 
   */
  @PostMapping("/folha-pagamento/add-contracheque")
  public String postAddContraCheque(HttpSession session, ContraCheque contraCheque, Model model) {

    List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();

    if (session.getAttribute("contrachequesUpdate") != null && session.getAttribute("folhaPagamento") != null) {

      contraCheques = (List<ContraCheque>) session.getAttribute("contrachequesUpdate");
      contraCheques.add(contraCheque);
      session.setAttribute("contrachequesUpdate", contraCheques);

      FolhaPagamento folhaPagamento = (FolhaPagamento) session.getAttribute("folhaPagamento");

      return "redirect:/folha-pagamento/update/" + folhaPagamento.getId();

    } else if (session.getAttribute("contracheques") != null) {

      contraCheques = (List<ContraCheque>) session.getAttribute("contracheques");

    }

    contraCheques.add(contraCheque);

    session.setAttribute("contracheques", contraCheques);

    return "redirect:/folha-pagamento/create";

  }

  @GetMapping("/folha-pagamento/pagar/todos")
  public ModelAndView getFolhaPagarTodos() {
    ModelAndView mv = new ModelAndView("pages/listFolhaPagar");
    mv.addObject("allFolhasPagamentos", folhaPagamentoService.findAll());
    return mv;
  }
  @GetMapping("/folha-pagamento/pagar")
  public ModelAndView getFolhaPagar() {
    ModelAndView mv = new ModelAndView("pages/listFolhaPagar");
    mv.addObject("allFolhasPagamentos", folhaPagamentoService.allFolhaPagar());
    return mv;
  }
  @GetMapping("/folha-pagamento/pagar/pago")
  public ModelAndView getFolhaPago() {
    ModelAndView mv = new ModelAndView("pages/listFolhaPagar");
    mv.addObject("allFolhasPagamentos", folhaPagamentoService.allFolhaPago());
    return mv;
  }
  @PostMapping({"/folha-pagamento/pagar/update"})
  public String updateFolhaPagar(FolhaPagamento f,
   RedirectAttributes redirectAttributes){
    
    f.setStatus(false);
    folhaPagamentoService.save(f);


    redirectAttributes.addAttribute("message_text","Sucesso ao realizar Pagamento da Folha");
    redirectAttributes.addAttribute("message_type","success");
    return HOME_PAGE;

  }

}
