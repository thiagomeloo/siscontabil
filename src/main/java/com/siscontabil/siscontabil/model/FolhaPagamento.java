package com.siscontabil.siscontabil.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "TB_FOLHA_PAGAMENTO")
public class FolhaPagamento {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ContraCheque> contraCheques;
  
  @ManyToOne
  @JoinColumn(name = "id_setor")
  private Setor setor;

  @Column(name = "competencia")
  private String competencia;

  @Column(name = "status", columnDefinition = "boolean default true")
  private boolean status = true;

  public double getValorTotal(){
    double total =0;
    for(int i =0; i<this.contraCheques.size();i++){
      total += contraCheques.get(i).getSalarioLiquido();
    }
    return total;
  }
  
  public int getQuantEmpregado(){
    int quant = 0;
      for(int i = 0; i < this.contraCheques.size();i++){
        quant++;
      }
    return quant;
  }
}


