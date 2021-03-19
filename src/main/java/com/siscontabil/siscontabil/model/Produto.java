package com.siscontabil.siscontabil.model;

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
@Table(name = "TB_PRODUTO")
public class Produto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(name = "descricao")
  private String descricao;

  @Column(name = "quantidade")
  private int quantidade;

  @Column(name = "valorDeCusto")
  private double valorDeCusto;

  @Column(name = "valorDeSaida")
  private double valorDeSaida;
  
  @ManyToOne
  @JoinColumn(name = "id_fornecedor")
  private Fornecedor fornecedor;
  


}
