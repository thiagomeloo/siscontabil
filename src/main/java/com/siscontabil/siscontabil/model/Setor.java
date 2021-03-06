package com.siscontabil.siscontabil.model;

import javax.persistence.*;
import lombok.*;

//lombok
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

//javaPersistence
@Embeddable
@Table(name = "TB_SETOR")
@Entity
public class Setor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;
  
}
