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
@Table(name = "TB_USUARIO")
public class Usuario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "login")
  private String login;

  @Column(name = "senha")
  private String senha;

  @Column(name = "tipo_usuario")
  private String tipoUsuario;

  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;

}
