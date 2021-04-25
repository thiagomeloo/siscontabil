package com.siscontabil.siscontabil.util;

import java.util.ArrayList;
import java.util.List;

import com.siscontabil.siscontabil.model.ContraCheque;
import com.siscontabil.siscontabil.model.FolhaPagamento;
import com.siscontabil.siscontabil.model.Funcionario;
import com.siscontabil.siscontabil.model.FuncionarioFuncao;

public class FolhaPagamentoAnual {
  
  private List<List<FolhaPagamento>> listFolhaGroupByMes = new ArrayList<List<FolhaPagamento>>();


  public FolhaPagamentoAnual(){
    for (int i = 0; i <= 12; i++) {
      listFolhaGroupByMes.add(new ArrayList<FolhaPagamento>());
    }
  }

  public FolhaPagamentoAnual(List<FolhaPagamento> folhasPagamento){
    for (int i = 0; i <= 12; i++) {
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

  public  List<List<FolhaPagamento>>  getAllFolhas(){

    return this.listFolhaGroupByMes;
  }
  public static void main(String[] args) {

    FolhaPagamento fp1 = new FolhaPagamento();
    fp1.setId(1);
    fp1.setCompetencia("01/2021");

    FolhaPagamento fp2 = new FolhaPagamento();
    fp2.setId(2);
    fp2.setCompetencia("02/2021");

    FolhaPagamento fp3 = new FolhaPagamento();
    fp3.setId(3);
    fp3.setCompetencia("03/2021");

    FolhaPagamento fp4 = new FolhaPagamento();
    fp4.setId(4);
    fp4.setCompetencia("04/2021");

    FolhaPagamento fp5 = new FolhaPagamento();
    fp5.setId(5);
    fp5.setCompetencia("05/2021");

    FolhaPagamento fp6 = new FolhaPagamento();
    fp6.setId(6);
    fp6.setCompetencia("06/2021");

    FolhaPagamento fp7 = new FolhaPagamento();
    fp7.setId(7);
    fp7.setCompetencia("07/2021");

    FolhaPagamento fp8 = new FolhaPagamento();
    fp8.setId(8);
    fp8.setCompetencia("08/2021");

    FolhaPagamento fp9 = new FolhaPagamento();
    fp9.setId(9);
    fp9.setCompetencia("09/2021");

    FolhaPagamento fp10 = new FolhaPagamento();
    fp10.setId(10);
    fp10.setCompetencia("10/2021");

    FolhaPagamento fp11 = new FolhaPagamento();
    fp11.setId(4);
    fp11.setCompetencia("11/2021");

    FolhaPagamento fp12 = new FolhaPagamento();
    fp12.setId(12);
    fp12.setCompetencia("12/2021");
    List<ContraCheque> c = new ArrayList<ContraCheque>();
    ContraCheque ct = new ContraCheque();
    Funcionario func = new Funcionario();
    FuncionarioFuncao ffc = new FuncionarioFuncao();
    ffc.setSalario(100);
    func.setFuncao(ffc);
    ct.setFuncionario(func);
    ct.setComissao(200);
    c.add(ct);

    fp12.setContraCheques(c);

    
    List<FolhaPagamento> lista = new ArrayList<FolhaPagamento>();
    lista.add(fp1);
    lista.add(fp2);
    lista.add(fp3);
    lista.add(fp4);
    lista.add(fp5);
    lista.add(fp6);
    lista.add(fp7);
    lista.add(fp8);
    lista.add(fp9);
    lista.add(fp10);
    lista.add(fp11);
    lista.add(fp12);




    FolhaPagamentoAnual folhaAnual = new FolhaPagamentoAnual(lista);
    

    System.out.println(folhaAnual.getValorFolhaByMes(1));
    
    

  }

}


