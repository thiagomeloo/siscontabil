package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.service.DadosBancarioService;
import com.siscontabil.siscontabil.model.DadosBancario;
import com.siscontabil.siscontabil.repository.DadosBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosBancarioServiceImpl implements DadosBancarioService{

    @Autowired
    DadosBancarioRepository repository;
  
    @Override
    public DadosBancario save(DadosBancario dadosBancario){
        return repository.save(dadosBancario);
    }

    @Override
    public DadosBancario findById(Long id){
        return repository.findById(id).get();
    }

    @Override
    public List<DadosBancario> findAll(){
        return repository.findAll();
    }

    
    
}
