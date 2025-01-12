package br.com.alura.literalura.model.respostaLivros;

import br.com.alura.literalura.model.livro.DadosLivro;
import br.com.alura.literalura.model.livro.Livro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosRespostaLivros(
        @JsonAlias("count") Integer count,
        @JsonAlias("results") ArrayList<DadosLivro> results) {
}
