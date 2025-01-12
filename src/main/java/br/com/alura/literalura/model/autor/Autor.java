package br.com.alura.literalura.model.autor;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
            ", Ano de nascimento: " + anoNascimento +
            ", Ano de falecimento: " + anoFalecimento;
    }
}
