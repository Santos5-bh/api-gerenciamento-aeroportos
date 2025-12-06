package com.aeroportos.gerenciamento_aeroportos.controller;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.service.AeroportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeroportos") 
public class AeroportoController {

    @Autowired
    private AeroportoService aeroportoService;

    
    @GetMapping
    public List<Aeroporto> listarTodos() {
        return aeroportoService.listarTodos();
    }

    
    @GetMapping("/{iata}")
    public ResponseEntity<Aeroporto> buscarPorIata(@PathVariable String iata) {
        Optional<Aeroporto> aeroporto = aeroportoService.buscarPorIata(iata);
        
        
        return aeroporto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Aeroporto> criarAeroporto(@RequestBody Aeroporto aeroporto) {
        Aeroporto novoAeroporto = aeroportoService.salvar(aeroporto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAeroporto);
    }

    
    @PutMapping("/{iata}")
    public ResponseEntity<Aeroporto> atualizarAeroporto(@PathVariable String iata, @RequestBody Aeroporto aeroportoAtualizado) {
        Optional<Aeroporto> aeroportoExistente = aeroportoService.buscarPorIata(iata);

        if (aeroportoExistente.isPresent()) {
            aeroportoAtualizado.setId(aeroportoExistente.get().getId()); 
            aeroportoAtualizado.setCodigoIata(iata); 
            Aeroporto salvo = aeroportoService.salvar(aeroportoAtualizado);
            return ResponseEntity.ok(salvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{iata}")
    public ResponseEntity<Void> deletarAeroporto(@PathVariable String iata) {
        Optional<Aeroporto> aeroporto = aeroportoService.buscarPorIata(iata);
        
        if (aeroporto.isPresent()) {
            aeroportoService.deletar(iata);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}