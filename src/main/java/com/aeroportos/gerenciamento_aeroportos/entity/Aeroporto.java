package com.aeroportos.gerenciamento_aeroportos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "aeroportos")

public class Aeroporto {
    @Id
    @Column(name = "id_aeroporto")
    private long id;

    @Column(name = "nome_aeroporto")
    private String nomeAeroporto;

    @Column(name = "codigo_iata", length = 3)
    private String codigoIata;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "codigo_pais_iso", length = 2)
    private String codigoPaisIso;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column (name = "altitude")
    private double altitude;
}