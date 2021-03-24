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
@Table(name = "TB_SETOR")
public class Setor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "descricao")
  private String descricao;
  
}
