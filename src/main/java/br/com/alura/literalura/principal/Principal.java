package br.com.alura.literalura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    \nEscolha o número de sua opção:
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    0- Sair\n
                    """);

            opcao = leitor.nextInt();
            leitor.nextLine();

            System.out.println("Sua opcao: "+ opcao);

            switch (opcao) {
                case 1:
//                    buscarLivro();
                    break;
                case 2:
//                    listarLivros();
                    break;
                case 3:
//                    listarAutores();
                    break;
                case 4:
//                    listarAutoresPorAno();
                    break;
                case 5:
//                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo do LiterAlura...");
                    break;
                default:
                    System.out.println("Opção inválida! Selecione uma opção entre 1 e 5, ou 0 para Sair.");
                    break;
            }
        }
    }
}
