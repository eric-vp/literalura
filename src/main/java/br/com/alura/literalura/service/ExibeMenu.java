package br.com.alura.literalura.service;

import java.util.Scanner;

public class ExibeMenu {
    public void exibeMenu() {
        Scanner leitor = new Scanner(System.in);
        var opcao = "-1";

        do {
            System.out.println("""
                    \nEscolha o número de sua opção:
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    0- Sair\n
                    """);

            opcao = leitor.nextLine();
            System.out.println("Sua opcao: "+ opcao);
        } while (!opcao.equalsIgnoreCase("0"));
    }
}
