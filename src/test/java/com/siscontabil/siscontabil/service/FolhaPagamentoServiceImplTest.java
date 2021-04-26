package com.siscontabil.siscontabil.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.siscontabil.siscontabil.model.ContraCheque;
import com.siscontabil.siscontabil.model.FolhaPagamento;
import com.siscontabil.siscontabil.model.Setor;
import com.siscontabil.siscontabil.repository.FolhaPagamentoRepository;
import com.siscontabil.siscontabil.service.serviceImplents.FolhaPagamentoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FolhaPagamentoServiceImplTest {
 
  @MockBean
  FolhaPagamentoRepository repository;

  @Autowired
  FolhaPagamentoServiceImpl folhaPagamentoService;


  private List<FolhaPagamento> folhas;
  private FolhaPagamento folha;

  @BeforeEach
  public void setup(){

    List<ContraCheque> contraCheques = new ArrayList<ContraCheque>();
    Setor setor = new Setor(1,"setor","sDesc",true);
    folha = new FolhaPagamento(1,contraCheques,setor,"01/2021",true);

    this.folhas = new LinkedList<>();
    folhas.add(folha);
  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarAllFolhaPagar(){
    
    doReturn(this.folhas)
      .when(repository)
      .allFolhaPagar();

    List<FolhaPagamento> folhasRetornadas = folhaPagamentoService.allFolhaPagar();

    assertEquals(1, folhasRetornadas.size());
    assertEquals(true, folhasRetornadas.get(0).isStatus());

  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarAllFolhaPago(){
    this.folha.setStatus(false);
    doReturn(this.folhas)
      .when(repository)
      .allFolhaPago();

    List<FolhaPagamento> folhasRetornadas = folhaPagamentoService.allFolhaPago();

    assertEquals(1, folhasRetornadas.size());
    assertEquals(false, folhasRetornadas.get(0).isStatus());

  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarAllFolhaPagamentoCompetencia(){
    this.folha.setStatus(false);
    doReturn(this.folhas)
      .when(repository)
      .allFolhaPagamentoCompetencia("2021");

    List<FolhaPagamento> folhasRetornadas = folhaPagamentoService.allFolhaPagamentoCompetencia("2021");

    assertEquals(1, folhasRetornadas.size());
    assertEquals("01/2021", folhasRetornadas.get(0).getCompetencia());

  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarAllFolhaPagamentoMes(){
    this.folha.setStatus(false);
    doReturn(this.folhas)
      .when(repository)
      .allFolhaPagamentoMes("01/2021");

    List<FolhaPagamento> folhasRetornadas = folhaPagamentoService.allFolhaPagamentoMes("01/2021");

    assertEquals(1, folhasRetornadas.size());
    assertEquals("01/2021", folhasRetornadas.get(0).getCompetencia());

  }


}
