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
@Table(name = "TB_FUNCIONARIO_FUNCAO")
public class FuncionarioFuncao {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "cargo")
  private String cargo;

  @Column(name = "salario")
  private double salario;

  @Column(name = "carga_horaria")
  private int cargaHoraria;

  @ManyToOne
  @JoinColumn(name = "setor")
  private Setor setor;
  
}
