package com.siscontabil.siscontabil.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import com.siscontabil.siscontabil.model.Cliente;
import com.siscontabil.siscontabil.model.Endereco;
import com.siscontabil.siscontabil.repository.ClienteRepository;
import com.siscontabil.siscontabil.service.serviceImplents.ClienteServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ClienteServiceImplTest {
 
  @MockBean
  ClienteRepository repository;

  @Autowired
  ClienteServiceImpl clienteService;


  private List<Cliente> clientes;
  Cliente cliente;

  @BeforeEach
  public void setup(){
    Endereco endereco = new Endereco(1,"rua",1,"bairro", "cidade","2301","uf");
    cliente = new Cliente(1,"Carla","123",true,endereco);
    this.clientes = new LinkedList<>();
    clientes.add(cliente);
  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarClienteAtivo(){
    
    doReturn(this.clientes)
      .when(repository)
      .allClienteAtivo();

    List<Cliente> clientesRetornados = clienteService.allClienteAtivo();

    assertEquals(1, clientesRetornados.size());
    assertEquals(true, clientesRetornados.get(0).isStatus());

  }

  @Test
  public void deveRetornarSucesso_QuandoBuscarClienteInativo(){
    this.cliente.setStatus(false);
    doReturn(this.clientes)
      .when(repository)
      .allClienteInativo();

    List<Cliente> clientesRetornados = clienteService.allClienteInativo();

    assertEquals(1, clientesRetornados.size());
    assertEquals(false, clientesRetornados.get(0).isStatus());

  }

}
