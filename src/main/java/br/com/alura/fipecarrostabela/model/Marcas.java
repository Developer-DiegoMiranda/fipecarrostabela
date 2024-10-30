package br.com.alura.fipecarrostabela.model;

public record Marcas(String codigo,
                     String nome) {
    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}
