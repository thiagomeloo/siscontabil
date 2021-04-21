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
@Table(name = "TB_CLIENTE")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf")
  private String CPF;

  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;

  @ManyToOne
  @JoinColumn(name = "id_endereco")
  private Endereco endereco;

}
