package br.com.alura.literalura.model.livro;

import br.com.alura.literalura.model.autor.Autor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Integer downloads;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.autor = new Autor(dadosLivro.autores().get(0));
//        this.autor = dadosLivro.autores().stream().map(
//                Autor::new
//        ).collect(Collectors.toList());
        this.idioma = dadosLivro.idiomas().get(0);
        this.downloads = dadosLivro.downloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutores() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
            ", Autor: " + autor +
            ", Idioma:" + idioma +
            ", Quantidade de downloads: " + downloads;
    }
}
