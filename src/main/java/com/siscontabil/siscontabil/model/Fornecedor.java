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
@Table(name = "TB_FORNECEDOR")
public class Fornecedor {
  

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cnpj")
  private String CNPJ;

  @ManyToOne
  @JoinColumn(name = "id_endereco")
  private Endereco endereco;

  @Column(name = "status")
  private boolean status;
  
}
