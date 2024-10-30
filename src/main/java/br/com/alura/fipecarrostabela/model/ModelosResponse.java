package br.com.alura.fipecarrostabela.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponse(List<Modelos> modelos) {}
