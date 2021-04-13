package com.siscontabil.siscontabil.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import com.siscontabil.siscontabil.model.Setor;
import com.siscontabil.siscontabil.repository.SetorRepository;
import com.siscontabil.siscontabil.service.serviceImplents.SetorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SetorServiceImplTest {
 
  @MockBean
  SetorRepository repository;

  @Autowired
  SetorServiceImpl setorService;


  private List<Setor> setores;
  private Setor setor;

  @BeforeEach
  public void setup(){
    setor = new Setor(1,"setor vendas", "descricao setor vendas", true);
    this.setores = new LinkedList<>();
    setores.add(setor);
  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarSetorPorNome(){
    
    doReturn(this.setores)
      .when(repository)
      .findByNome(this.setor.getNome());

    List<Setor> setoresRetornados = setorService.findByNome(this.setor.getNome());

    assertEquals(1, setoresRetornados.size());
    assertEquals(this.setor.getNome(), setoresRetornados.get(0).getNome());

  }

}
