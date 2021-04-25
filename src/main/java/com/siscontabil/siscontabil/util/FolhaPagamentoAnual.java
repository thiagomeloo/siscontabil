package com.siscontabil.siscontabil.util;

import java.util.ArrayList;
import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;

public class FolhaPagamentoAnual {

  private List<List<FolhaPagamento>> listFolhaGroupByMes = new ArrayList<List<FolhaPagamento>>();

  public FolhaPagamentoAnual() {
    generateList();
  }

  public FolhaPagamentoAnual(List<FolhaPagamento> folhaPagamentos) {
    generateList();
    for (int i = 0; i < folhaPagamentos.size(); i++) {

      FolhaPagamento folhaPagamento = folhaPagamentos.get(i);

      // verifica se e diferente de null
      if (folhaPagamento != null && folhaPagamento.getCompetencia() != null) {

        String[] str = folhaPagamento.getCompetencia().split("/");
        int mes = Integer.parseInt(str[0]);

        // valida o mes e adiciona
        if (validMes(mes)) {
          add(mes, folhaPagamento);

        }

      }

    }

  }

  private void generateList() {
    for (int i = 0; i < 12; i++) {
      this.listFolhaGroupByMes.add(new ArrayList<FolhaPagamento>());
    }
  }

  public int getSize() {
    return this.listFolhaGroupByMes.size();
  }

  public boolean validMes(int mes) {
    if (mes < 0 || mes > 12) {
      return false;
    } else {
      return true;
    }
  }

  public void add(int mes, FolhaPagamento folhaPagamento) {
    mes--;
    if (validMes(mes)) {
      List<FolhaPagamento> f = this.listFolhaGroupByMes.get(mes);
      f.add(folhaPagamento);
      this.listFolhaGroupByMes.set(mes, f);
    }

  }

  public List<FolhaPagamento> getFolhasByMes(int mes) {
    mes--;
    if (validMes(mes)) {
      return this.listFolhaGroupByMes.get(mes);
    }
    return null;

  }

  public double getValorTotalFolhaByMes(int mes) {
    mes--;
    if (validMes(mes)) {
      try {

        double valorTotalFolhasDoMes = 0;
        List<FolhaPagamento> f = this.listFolhaGroupByMes.get(mes);
        for (int i = 0; i < f.size(); i++) {
          valorTotalFolhasDoMes += f.get(i).getValorTotal();
        }
        return valorTotalFolhasDoMes;

      } catch (Exception e) {

        return 0;

      }

    }
    return 0;
  }

  public int getQuantTotalFolhasByMes(int mes){
    mes --;
    if(validMes(mes)){
      int qtdFolhasMes = 0;
      List<FolhaPagamento> f = this.listFolhaGroupByMes.get(mes);
      for (int i = 0; i < f.size(); i++) {
        qtdFolhasMes++;
      }
      return qtdFolhasMes;
    }
    return 0;
  }

  public double getValorTotal() {
    double valorTotalAnual = 0;
    for (int i = 0; i < this.listFolhaGroupByMes.size() + 1; i++) {
      if (validMes(i)) {
        valorTotalAnual += getValorTotalFolhaByMes(i);
      }
    }
    return valorTotalAnual;
  }

  public int getQuantTotalEmpregadosFolhaByMes(int mes) {
    mes--;
    if (validMes(mes)) {

      int qtdEmpregadosTotal = 0;
      List<FolhaPagamento> f = this.listFolhaGroupByMes.get(mes);

      for (int i = 0; i < f.size(); i++) {
        qtdEmpregadosTotal += f.get(i).getQuantEmpregado();
      }
      return qtdEmpregadosTotal;
    }

    return 0;
  }

  public int getQuantEmpregadoTotal() {
    int valorTotalEmpregados = 0;
    
    for (int i = 0; i < this.listFolhaGroupByMes.size() + 1; i++) {
      
      if (validMes(i)) {
        valorTotalEmpregados += getQuantTotalEmpregadosFolhaByMes(i);
      }

    }
    return valorTotalEmpregados;
  }

  public  List<List<FolhaPagamento>>  getAllFolhas(){
    return this.listFolhaGroupByMes;
  }


}
