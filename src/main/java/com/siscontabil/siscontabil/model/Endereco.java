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
@Table(name = "TB_ENDERECO")
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "logradouro")
  private String logradouro;
  
  @Column(name = "numero")
  private int numero;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "cep")
  private String cep;

  @Column(name = "uf")
  private String UF;

}
