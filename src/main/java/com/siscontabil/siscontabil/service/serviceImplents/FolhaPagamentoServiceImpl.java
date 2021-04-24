package com.siscontabil.siscontabil.service.serviceImplents;

import java.util.List;

import com.siscontabil.siscontabil.model.FolhaPagamento;
import com.siscontabil.siscontabil.repository.FolhaPagamentoRepository;
import com.siscontabil.siscontabil.service.FolhaPagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolhaPagamentoServiceImpl implements FolhaPagamentoService{

    @Autowired
    FolhaPagamentoRepository repository;

    @Override
    public List<FolhaPagamento> findAll() {
        return repository.findAll();
    }

    @Override
    public FolhaPagamento save(FolhaPagamento folhaPagamento) {
        return repository.save(folhaPagamento);
    }

    @Override
    public FolhaPagamento findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<FolhaPagamento> allFolhaPagamentoCompetencia( String competencia) {
        return repository.allFolhaPagamentoCompetencia(competencia);
    }

    @Override
    public List<FolhaPagamento> allFolhaPagamentoMes(String competencia) {
        return repository.allFolhaPagamentoMes(competencia);
    }



    
}
