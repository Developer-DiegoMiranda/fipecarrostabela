package br.com.alura.fipecarrostabela.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosValor(
//        int TipoVeiculo,
        String Valor,
        String Marca,
        String Modelo,
        int AnoModelo,
        String Combustivel
//        String CodigoFipe,
//        String MesReferencia,
//        String SiglaCombustivel
        ) {}
