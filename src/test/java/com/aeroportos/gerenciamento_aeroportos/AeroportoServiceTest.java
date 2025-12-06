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
import com.aeroportos.gerenciamento_aeroportos.exception.AeroportoNaoEncontradoException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AeroportoServiceTest {

    @InjectMocks
    private AeroportoService aeroportoService;

    @Mock
    private AeroportoRepository aeroportoRepository;

    
    @Test
    public void testeConverterPesParaMetros() {
        
        double resultado = aeroportoService.converterPesParaMetros(1000.0);
        Assertions.assertEquals(304.8, resultado, 0.01);
    }

    @Test
    public void testeObterIsoPais() {
        
        String iso = aeroportoService.obterIsoPais("Brazil");
        Assertions.assertEquals("BR", iso);
    }

    

    @Test
    public void deveLancarErro_QuandoBuscarIataInexistente() {
        
        Mockito.when(aeroportoRepository.findByCodigoIata("ZZZ")).thenReturn(Optional.empty());

        Assertions.assertThrows(AeroportoNaoEncontradoException.class, () -> {
            aeroportoService.buscarPorIata("ZZZ");
        });
    }

    @Test
    public void deveLancarErro_QuandoSalvarIataInvalido() {
        
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCodigoIata("ABCD"); 
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            aeroportoService.salvar(aeroporto);
        });
    }
}