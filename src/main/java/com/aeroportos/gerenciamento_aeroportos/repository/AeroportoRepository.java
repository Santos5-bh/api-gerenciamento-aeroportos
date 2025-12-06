package com.aeroportos.gerenciamento_aeroportos.repository;

import com.aeroportos.gerenciamento_aeroportos.entity.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

    
    Optional<Aeroporto> findByCodigoIata(String codigoIata);

    
    void deleteByCodigoIata(String codigoIata);

    
    boolean existsByCodigoIata(String codigoIata);
}