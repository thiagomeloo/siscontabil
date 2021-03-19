package com.siscontabil.siscontabil.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

//lombok
@Data
//javaPersistence
@Entity
@Table(name = "TB_VENDA")
public class Venda {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Produto> itens;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

  @Column(name = "data_venda")
  private Date dataVenda;

  @ManyToOne
  @JoinColumn(name = "id_forma_pagamento")
  private FormaPagamento formaPagamento;

}
