package com.siscontabil.siscontabil.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//lombok
@Data
//javaPersistence
@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "sexo")
  private String sexo;

  @Column(name = "nomePai")
  private String nomePai;

  @Column(name = "nomeMae")
  private String nomeMae;

  @Column(name = "nacionalidade")
  private String nacionalidade;

  @Column(name = "naturalidade")
  private String naturalidade;

  @Column(name = "escolaridade")
  private String escolaridade;

  @Column(name = "CPF")
  private String CPF;

  @Column(name = "RG")
  private String RG;

  @Column(name = "titulo_eleitor")
  private String tituloEleitor;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "data_admissao")
  private Date dataAdmissao;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column (name = "data_demissao")
  private Date dataDemissao;
  
  @ManyToOne
  @JoinColumn(name = "id_endereco")
  private Endereco endereco;
  
  @ManyToOne
  @JoinColumn(name = "id_funcionario_funcao")
  private FuncionarioFuncao funcao;

  @ManyToOne
  @JoinColumn(name = "id_dados_bancarios")
  private DadosBancario dadosBancario;
  
  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;
  
  public double getPorcetagemInss( ){
    double porcentagem = 0;
    if(this.funcao.getSalario() <= 1100){
      porcentagem = 7.5;
    }else if(this.funcao.getSalario() >= 1100.1 && this.funcao.getSalario() <= 2203.48 ){
      porcentagem= 9;
    }else if(this.funcao.getSalario() >= 2203.49 && this.funcao.getSalario() <= 3305.22){
      porcentagem = 12;
    }else if(this.funcao.getSalario() >= 3305.23){
      porcentagem = 14;
    }
  return porcentagem;
  }

}
