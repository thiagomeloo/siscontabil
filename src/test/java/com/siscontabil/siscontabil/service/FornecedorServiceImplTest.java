package com.siscontabil.siscontabil.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.model.Fornecedor;
import com.siscontabil.siscontabil.repository.FornecedorRepository;
import com.siscontabil.siscontabil.service.serviceImplents.FornecedorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FornecedorServiceImplTest {
 
  @MockBean
  FornecedorRepository repository;

  @Autowired
  FornecedorServiceImpl fornecedorService;


  private List<Fornecedor> fornecedores;

  @BeforeEach
  public void setup(){
    Endereco endereco = new Endereco(1,"rua",1,"bairro", "cidade","2301","uf");
    Fornecedor fornecedor = new Fornecedor(1,"joao","101020",endereco,true);
    this.fornecedores = new LinkedList<>();
    fornecedores.add(fornecedor);
  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarFornecedorAtivo(){
    
    doReturn(this.fornecedores)
      .when(repository)
      .allFornecedorAtivo();

    List<Fornecedor> fornecedoresRetornados = fornecedorService.allFornecedorAtivo();

    assertEquals(1, fornecedoresRetornados.size());
    assertEquals(true, fornecedoresRetornados.get(0).isStatus());

  }

}
