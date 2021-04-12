package com.siscontabil.siscontabil.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//javaPersistence
@Entity
@Table(name = "TB_CONTRA_CHEQUE")
public class ContraCheque {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "id_funcionario")
  Funcionario funcionario;

  @Column (name = "comissao")
  double comissao;

  @Column (name = "desconto_INSS")
  double descontoINSS;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column (name = "data_emissao")
  private Date dataEmissao;
  
  
}
