package com.siscontabil.siscontabil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;
  
}
