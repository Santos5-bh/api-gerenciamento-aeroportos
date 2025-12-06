package com.aeroportos.gerenciamento_aeroportos.service;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    public List<Aeroporto> listarTodos() {
        return aeroportoRepository.findAll();
    }

    public Optional<Aeroporto> buscarPorIata(String iata) {
        return aeroportoRepository.findByCodigoIata(iata);
    }

    @Transactional
    public Aeroporto salvar(Aeroporto aeroporto) {
        
        if (aeroporto.getId() == null && aeroportoRepository.existsByCodigoIata(aeroporto.getCodigoIata())) {
            throw new IllegalArgumentException("Aeroporto com código IATA " + aeroporto.getCodigoIata() + " já existe.");
        }
        return aeroportoRepository.save(aeroporto);
    }
    
    @Transactional
    public void deletar(String iata) {
         if (!aeroportoRepository.existsByCodigoIata(iata)) {
            throw new IllegalArgumentException("Aeroporto não encontrado para exclusão.");
        }
        aeroportoRepository.deleteByCodigoIata(iata);
    }
}