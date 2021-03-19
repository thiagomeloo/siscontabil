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

  @Column(name = "data_admissao")
  private Date dataAdmissao;

  @ManyToOne
  @JoinColumn(name = "id_endereco")
  private Endereco endereco;
  
  @ManyToOne
  @JoinColumn(name = "id_funcionario_funcao")
  private FuncionarioFuncao funcao;

  @ManyToOne
  @JoinColumn(name = "id_dados_bancarios")
  private DadosBancario dadosBancario;
  

  
  
}
