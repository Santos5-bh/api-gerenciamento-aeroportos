package com.aeroportos.gerenciamento_aeroportos.service;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.exception.AeroportoNaoEncontradoException; // Importe a exceção nova
import com.aeroportos.gerenciamento_aeroportos.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    public List<Aeroporto> listarTodos() {
        return aeroportoRepository.findAll();
    }

    public Aeroporto buscarPorIata(String iata) {
        
        return aeroportoRepository.findByCodigoIata(iata)
                .orElseThrow(() -> new AeroportoNaoEncontradoException("Aeroporto não encontrado com IATA: " + iata));
    }

    @Transactional
    public Aeroporto salvar(Aeroporto aeroporto) {
        
        validarAeroporto(aeroporto);

        
        if (aeroporto.getId() == null && aeroportoRepository.existsByCodigoIata(aeroporto.getCodigoIata())) {
            throw new IllegalArgumentException("Aeroporto já existe.");
        }
        return aeroportoRepository.save(aeroporto);
    }
    
    @Transactional
    public void deletar(String iata) {
         if (!aeroportoRepository.existsByCodigoIata(iata)) {
            throw new AeroportoNaoEncontradoException("Aeroporto não encontrado para exclusão.");
        }
        aeroportoRepository.deleteByCodigoIata(iata);
    }

    
    public double converterPesParaMetros(double pes) {
        return pes * 0.3048;
    }

    
    public String obterIsoPais(String nomePais) {
        if ("Brazil".equalsIgnoreCase(nomePais) || "Brasil".equalsIgnoreCase(nomePais)) {
            return "BR";
        } else if ("United States".equalsIgnoreCase(nomePais)) {
            return "US";
        }
        return "XX"; 
    }

    private void validarAeroporto(Aeroporto aeroporto) {
        if (aeroporto.getCodigoIata() == null || aeroporto.getCodigoIata().length() != 3) {
            throw new IllegalArgumentException("Código IATA deve ter exatamente 3 letras.");
        }
        if (aeroporto.getAltitude() != null && aeroporto.getAltitude() < 0) {
            throw new IllegalArgumentException("Altitude não pode ser negativa.");
        }
    }
}