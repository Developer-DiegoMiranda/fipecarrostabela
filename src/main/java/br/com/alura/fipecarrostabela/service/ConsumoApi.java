package br.com.alura.fipecarrostabela.service;

import br.com.alura.fipecarrostabela.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsumoApi {

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public ConsumoApi() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Marcas> obterMarcas() {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), new TypeReference<List<Marcas>>() {});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter marcas", e);
        }
    }

    public List<Modelos> obterModelos(int codigoMarca) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ModelosResponse modeloResponse = objectMapper.readValue(response.body(), ModelosResponse.class);
            return modeloResponse.modelos();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter modelos", e);
        }
    }

    public List<String> obterAnosModelo(int codigoMarca, int codigoModelo) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Map<String, String>> anos = objectMapper.readValue(response.body(), new TypeReference<>() {});
            return anos.stream().map(ano -> ano.get("codigo")).toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter anos para o modelo", e);
        }
    }

    public Optional<ModelosValor> obterValorModelo(int codigoMarca, int codigoModelo, String anoModelo) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + anoModelo;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(objectMapper.readValue(response.body(), ModelosValor.class));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter valor do modelo", e);
        }
    }
}
