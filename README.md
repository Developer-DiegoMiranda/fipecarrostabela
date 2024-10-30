# Fipe Carros Tabela

Este projeto Java utiliza a API de Tabela Fipe para obter e exibir informações sobre marcas, modelos e valores de veículos.
É uma aplicação de console baseada no Spring Boot que permite ao usuário escolher uma marca e buscar modelos específicos para visualizar os dados de cada ano de avaliação disponível.

# Funcionalidades
1. Listar Marcas de Carros: Exibe uma lista de marcas disponíveis na API.
2. Filtrar Modelos por Nome: Solicita ao usuário um trecho do nome do modelo para filtrar os resultados.
3. Exibir Detalhes por Ano: Para o modelo selecionado, exibe os dados para todos os anos de avaliação disponíveis.


Este projeto utiliza a API pública da Tabela Fipe: https://deividfortuna.github.io/fipe/?ref=public_apis

# Endpoints Principais:

https://parallelum.com.br/fipe/api/v1/carros/marcas

https://parallelum.com.br/fipe/api/v1/carros/marcas/{codigoMarca}/modelos/{codigoModelo}/anos

https://parallelum.com.br/fipe/api/v1/carros/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{anoModelo}
