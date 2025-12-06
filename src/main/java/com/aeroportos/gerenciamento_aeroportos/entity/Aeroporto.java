package com.aeroportos.gerenciamento_aeroportos.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aeroportos")
public class Aeroporto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeroporto") 
    private Long id;

    @Column(name = "nome_aeroporto", nullable = false)
    private String nome;

    @Column(name = "codigo_iata", length = 3, unique = true) 
    private String codigoIata;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "codigo_pais_iso", length = 2) 
    private String codigoPaisIso;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Double altitude;

    public Aeroporto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigoIata() { return codigoIata; }
    public void setCodigoIata(String codigoIata) { this.codigoIata = codigoIata; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCodigoPaisIso() { return codigoPaisIso; }
    public void setCodigoPaisIso(String codigoPaisIso) { this.codigoPaisIso = codigoPaisIso; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }
}