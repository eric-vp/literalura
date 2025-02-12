# Literalura - Catálogo de Livros e Autores

## Visão Geral

Literalura é um sistema para busca, cadastro e exibição de livros e autores. O sistema permite aos usuários buscar livros por título, listar todos os livros e autores registrados, e exibir estatísticas como a quantidade de livros em determinados idiomas ou autores vivos em anos específicos.

## Funcionalidades

- **Busca de Livro por Título:** Permite ao usuário buscar livros pelo título utilizando a API Gutendex.

- **Listagem de Livros Registrados:** Exibe todos os livros que foram cadastrados no sistema.

- **Listagem de Autores Registrados:** Exibe todos os autores cadastrados.

- **Listagem de Autores Vivos em um Ano:** Permite consultar autores vivos em um determinado ano.

- **Listagem de Livros por Idioma:** Exibe a quantidade de livros cadastrados em idiomas específicos.

## Requisitos

- Java 17+

- Spring Boot 3.4.0+

- Maven

- Banco de Dados: PostgreSQL ou qualquer outro banco de dados suportado pelo Spring Data JPA.
  
## Uso

Ao iniciar a aplicação, o menu principal será exibido com as seguintes opções:

- **1 - Buscar livro pelo título:** Insira o título do livro para buscar e cadastrá-lo no sistema.

- **2 - Listar livros registrados:** Exibe todos os livros cadastrados.

- **3 - Listar autores registrados:** Exibe todos os autores cadastrados.

- **4 - Listar autores vivos em um determinado ano:** Insira um ano para listar autores vivos naquele ano.

- **5 - Listar livros em um determinado idioma:** Escolha um idioma para listar os livros naquele idioma.

- **0 - Sair:** Encerra a aplicação.

## Tecnologias Utilizadas

- Spring Boot: Framework principal para o desenvolvimento da aplicação.

- Spring Data JPA: Para interação com o banco de dados.

- Hibernate: Implementação de JPA para persistência de dados.

- Jackson: Para manipulação de JSON.

- PostgreSQL: Banco de dados relacional utilizado para persistência dos dados.

## Autor

[<img loading="lazy" src="https://github.com/eric-vp.png" width=50><br><sub>Eric Palavro</sub>](https://www.linkedin.com/in/eric-vieira-palavro/)


