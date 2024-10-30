package br.com.alura.fipecarrostabela.application;

import br.com.alura.fipecarrostabela.model.Marcas;
import br.com.alura.fipecarrostabela.model.Modelos;
import br.com.alura.fipecarrostabela.model.ModelosValor;
import br.com.alura.fipecarrostabela.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class FipecarrosApplication implements CommandLineRunner {

	private final ConsumoApi consumoApi;

	public FipecarrosApplication() {
		this.consumoApi = new ConsumoApi();
	}

	public static void main(String[] args) {
		SpringApplication.run(FipecarrosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		try {
			// Obtenha a lista de marcas e exiba no console
			List<Marcas> marcas = consumoApi.obterMarcas();
			System.out.println("Escolha uma marca pelo código:");
			marcas.forEach(marca -> System.out.println(marca.codigo() + " - " + marca.nome()));

			// Solicitar código da marca
			System.out.print("Digite o código da marca: ");
			int codigoMarca = scanner.nextInt();

			// Obtenha e exiba o nome da marca selecionada
			Optional<Marcas> marcaSelecionada = marcas.stream()
					.filter(marca -> marca.codigo().equals(String.valueOf(codigoMarca)))
					.findFirst();

			if (marcaSelecionada.isPresent()) {
				System.out.println("Marca selecionada: " + marcaSelecionada.get().nome());
			} else {
				System.out.println("Código de marca não encontrado.");
				return;
			}

			// Obtenha a lista de modelos da marca selecionada
			List<Modelos> modelos = consumoApi.obterModelos(codigoMarca);
			if (modelos.isEmpty()) {
				System.out.println("Nenhum modelo encontrado para essa marca.");
				return;
			}

			// Solicitar um trecho do nome do modelo
			System.out.print("Digite um trecho do nome do carro a ser buscado: ");
			scanner.nextLine(); // Consome a quebra de linha após o próximoInt
			String nomeModelo = scanner.nextLine();

			// Filtrar modelos com base no trecho do nome
			List<Modelos> modelosFiltrados = modelos.stream()
					.filter(modelo -> modelo.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
					.toList();

			// Exibir apenas os modelos que contêm o trecho buscado
			if (modelosFiltrados.isEmpty()) {
				System.out.println("Nenhum modelo encontrado com o nome fornecido.");
				return;
			}

			System.out.println("Modelos encontrados:");
			modelosFiltrados.forEach(modelo -> System.out.println("Código: " + modelo.codigo() + " - Nome: " + modelo.nome()));

			// Solicitar o código do modelo escolhido
			System.out.print("Digite o código do modelo desejado para ver detalhes: ");
			int codigoModelo = scanner.nextInt();

			// Obter todos os anos disponíveis para o modelo selecionado
			List<String> anosDisponiveis = consumoApi.obterAnosModelo(codigoMarca, codigoModelo);
			if (anosDisponiveis.isEmpty()) {
				System.out.println("Nenhum ano disponível para o modelo selecionado.");
				return;
			}

			System.out.println("\nAnos disponíveis para o modelo selecionado:");
			for (String anoModelo : anosDisponiveis) {
				System.out.println("Ano: " + anoModelo);

				// Obter e exibir detalhes do modelo para cada ano disponível
				Optional<ModelosValor> valorModelo = consumoApi.obterValorModelo(codigoMarca, codigoModelo, anoModelo);
				valorModelo.ifPresentOrElse(
						valor -> {
							System.out.println("Detalhes para o ano " + anoModelo + ":");
							System.out.println("Valor: " + valor.Valor());
							System.out.println("Marca: " + valor.Marca());
							System.out.println("Modelo: " + valor.Modelo());
							System.out.println("Ano Modelo: " + valor.AnoModelo());
							System.out.println("Combustível: " + valor.Combustivel());
							System.out.println("----------------------------------------");
						},
						() -> System.out.println("Não foi possível obter os detalhes do ano " + anoModelo)
				);
			}
		} catch (Exception e) {
			System.out.println("Erro durante a execução: " + e.getMessage());
			e.printStackTrace();
		} finally {
			System.exit(0); // Encerra a aplicação após a execução
		}
	}
}
