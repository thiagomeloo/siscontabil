package com.siscontabil.siscontabil.model;

import java.sql.Date;
import java.util.List;


public class Venda {
  
  private long id;
  private Usuario usuario;
  private List<Produto> itens;
  private Cliente cliente;
  private Date dataVenda;
  private FormaPagamento formaPagamento;

}
