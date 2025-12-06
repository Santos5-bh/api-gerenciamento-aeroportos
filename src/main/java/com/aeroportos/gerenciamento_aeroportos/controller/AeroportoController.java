package com.aeroportos.gerenciamento_aeroportos.controller;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.service.AeroportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        
        Aeroporto aeroporto = aeroportoService.buscarPorIata(iata);
        return ResponseEntity.ok(aeroporto);
    }

    @PostMapping
    public ResponseEntity<Aeroporto> criarAeroporto(@RequestBody Aeroporto aeroporto) {
        Aeroporto novo = aeroportoService.salvar(aeroporto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{iata}")
    public ResponseEntity<Aeroporto> atualizarAeroporto(@PathVariable String iata, @RequestBody Aeroporto aeroportoAtualizado) {
        
        Aeroporto aeroportoExistente = aeroportoService.buscarPorIata(iata);
        
        aeroportoAtualizado.setId(aeroportoExistente.getId());
        aeroportoAtualizado.setCodigoIata(iata); 
        
        Aeroporto salvo = aeroportoService.salvar(aeroportoAtualizado);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{iata}")
    public ResponseEntity<Void> deletarAeroporto(@PathVariable String iata) {
        aeroportoService.deletar(iata);
        return ResponseEntity.noContent().build();
    }
}