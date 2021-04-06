package com.siscontabil.siscontabil.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//lombok
@Data
//javaPersistence
@Entity
@Table(name = "TB_DADOS_BANCARIO")
public class DadosBancario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idDadosBancario;

  @Column(name = "agencia")
  private String agencia;

  @Column(name = "conta")
  private String conta;

  @Column(name = "banco")
  private String banco;

}
