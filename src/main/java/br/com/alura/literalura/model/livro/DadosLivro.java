package br.com.alura.literalura.model.livro;

import br.com.alura.literalura.model.autor.Autor;
import br.com.alura.literalura.model.autor.DadosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") ArrayList<DadosAutor> autores,
        @JsonAlias("languages") ArrayList<String> idiomas,
        @JsonAlias("download_count") Integer downloads) {
}
