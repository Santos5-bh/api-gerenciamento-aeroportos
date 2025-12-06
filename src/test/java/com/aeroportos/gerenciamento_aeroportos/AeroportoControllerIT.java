package com.aeroportos.gerenciamento_aeroportos;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.repository.AeroportoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AeroportoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AeroportoRepository aeroportoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        aeroportoRepository.deleteAll(); 
    }

    @Test
    void deveCriarEBuscarAeroporto() throws Exception {
       
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setNome("Aeroporto de Teste");
        aeroporto.setCodigoIata("TST");
        aeroporto.setCidade("Cidade Teste");
        aeroporto.setCodigoPaisIso("BR");

        
        mockMvc.perform(post("/api/v1/aeroportos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aeroporto)))
                .andExpect(status().isCreated()) 
                .andExpect(jsonPath("$.codigoIata").value("TST"));

        
        mockMvc.perform(get("/api/v1/aeroportos/TST"))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.nome").value("Aeroporto de Teste"));
    }

    @Test
    void deveDeletarAeroporto() throws Exception {
       
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setNome("Para Deletar");
        aeroporto.setCodigoIata("DEL");
        aeroportoRepository.save(aeroporto);

       
        mockMvc.perform(delete("/api/v1/aeroportos/DEL"))
                .andExpect(status().isNoContent()); 

        
        mockMvc.perform(get("/api/v1/aeroportos/DEL"))
                .andExpect(status().isNotFound());
    }
}