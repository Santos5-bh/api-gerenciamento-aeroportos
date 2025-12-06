package com.aeroportos.gerenciamento_aeroportos; 

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import com.aeroportos.gerenciamento_aeroportos.repository.AeroportoRepository;
import com.aeroportos.gerenciamento_aeroportos.service.AeroportoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AeroportoServiceTest {

    @InjectMocks
    private AeroportoService aeroportoService;

    @Mock
    private AeroportoRepository aeroportoRepository;

    @Test
    public void deveRetornarAeroporto_QuandoBuscarPorIataExistente() {
        
        Aeroporto mockAeroporto = new Aeroporto();
        mockAeroporto.setCodigoIata("GRU");
        mockAeroporto.setNome("Guarulhos");

        
        Mockito.when(aeroportoRepository.findByCodigoIata("GRU")).thenReturn(Optional.of(mockAeroporto));

        
        Optional<Aeroporto> resultado = aeroportoService.buscarPorIata("GRU");

        
        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Guarulhos", resultado.get().getNome());
    }

    @Test
    public void deveLancarErro_QuandoTentarSalvarIataDuplicado() {
        
        Aeroporto novoAeroporto = new Aeroporto();
        novoAeroporto.setCodigoIata("GRU"); 

        
        Mockito.when(aeroportoRepository.existsByCodigoIata("GRU")).thenReturn(true);

        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            aeroportoService.salvar(novoAeroporto);
        });
    }
}