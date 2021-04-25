package com.siscontabil.siscontabil.util;

import java.util.ArrayList;
import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;

public class FolhaPagamentoAnual {
  
  private List<List<FolhaPagamento>> listFolhaGroupByMes = new ArrayList<List<FolhaPagamento>>();


  public FolhaPagamentoAnual(){
    for (int i = 0; i < 12; i++) {
      listFolhaGroupByMes.add(new ArrayList<FolhaPagamento>());
    }
  }

  public FolhaPagamentoAnual(List<FolhaPagamento> folhasPagamento){
    for (int i = 0; i < 12; i++) {
      listFolhaGroupByMes.add(new ArrayList<FolhaPagamento>());
    }

    for (int i = 0; i < folhasPagamento.size(); i++) {
      FolhaPagamento folhaPagamento = folhasPagamento.get(i);
      String[] str = folhaPagamento.getCompetencia().split("/");
      int mes = Integer.parseInt(str[0]);
      add(mes, folhaPagamento);
    }

  }

  public void add(int mes,FolhaPagamento folhaPagamento){
    List<FolhaPagamento> f = listFolhaGroupByMes.get(mes);
    f.add(folhaPagamento);
    listFolhaGroupByMes.set(mes, f);
  }

  public List<FolhaPagamento> getFolhaByMes(int mes){
    return listFolhaGroupByMes.get(mes);
  }

  public double getValorFolhaByMes(int mes){
    double valorTotalMes = 0;
    List<FolhaPagamento> f = listFolhaGroupByMes.get(mes);
    for (int i = 0; i < f.size() ; i++) {
      valorTotalMes += f.get(i).getValorTotal();
    }
    return valorTotalMes;
  }

  public double getValorTotal(){
    double valorTotalAnual = 0;
    for (int i = 0; i < listFolhaGroupByMes.size() ; i++) {
      valorTotalAnual += getValorFolhaByMes(i);
    }
    return valorTotalAnual;
  }

  public int getQuantEmpregadoByMes(int mes){
    int qtdEmpregadosTotal = 0;
    List<FolhaPagamento> f = listFolhaGroupByMes.get(mes);
    for (int i = 0; i < f.size() ; i++) {
      qtdEmpregadosTotal += f.get(i).getQuantEmpregado();
    }
    return qtdEmpregadosTotal;
  }

  public int getQuantEmpregadoTotal(){
    int valorTotalEmpregados = 0;
    for (int i = 0; i < listFolhaGroupByMes.size() ; i++) {
      valorTotalEmpregados += getQuantEmpregadoByMes(i);
    }
    return valorTotalEmpregados;
  }

  public static void main(String[] args) {

    FolhaPagamento fp1 = new FolhaPagamento();
    fp1.setId(1);
    fp1.setCompetencia("01/2021");

    FolhaPagamento fp2 = new FolhaPagamento();
    fp2.setId(2);
    fp2.setCompetencia("01/2021");

    
    List<FolhaPagamento> lista = new ArrayList<FolhaPagamento>();
    lista.add(fp1);
    lista.add(fp2);

    FolhaPagamentoAnual folhaAnual = new FolhaPagamentoAnual(lista);
    
    System.out.println(folhaAnual.getFolhaByMes(1));
    

  }

}


