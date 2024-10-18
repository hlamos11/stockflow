package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
public class GeneratorHistDTO {

    private Timestamp fecha;
    private Integer id;
    private String sitio;
    private String provincia;
    private String region;
    private Integer maxNivel;
    private Integer nivelActual;
    private Integer suministro;
    private Integer horas;
    private Double costo;
    private Double nivelPrevio;
    private Double nivelFinal;

    private Integer recargar;

    public GeneratorHistDTO(Timestamp fecha, Integer id, String sitio, String provincia, String region, Integer maxNivel, Integer nivelActual, Integer suministro, Integer horas, Double costo, Double nivelPrevio, Double nivelFinal, Integer recargar) {
        this.fecha = fecha;
        this.id = id;
        this.sitio = sitio;
        this.provincia = provincia;
        this.region = region;
        this.maxNivel = maxNivel;
        this.nivelActual = nivelActual;
        this.suministro = suministro;
        this.horas = horas;
        this.costo = costo;
        this.nivelPrevio = nivelPrevio;
        this.nivelFinal = nivelFinal;
        this.recargar = recargar;
    }
}
