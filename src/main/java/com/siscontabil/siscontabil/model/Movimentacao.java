package com.siscontabil.siscontabil.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//lombok
@Data
//javaPersistence
@Entity
@Table(name = "TB_MOVIMENTACAO")
public class Movimentacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "tipo")
  private String tipo;

  @Column(name = "valor")
  private double valor;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column (name = "data_movimentacao")
  private Date dataMovimentacao;

  
}
