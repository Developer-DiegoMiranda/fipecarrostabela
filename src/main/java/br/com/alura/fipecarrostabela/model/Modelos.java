package br.com.alura.fipecarrostabela.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record Modelos(String codigo,
                      String nome) {
    @Override
    public String toString() {
        return "Código: " + codigo + " - Nome: " + nome;
    }

}
