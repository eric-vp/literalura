package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.autor.Autor;
import br.com.alura.literalura.model.livro.DadosLivro;
import br.com.alura.literalura.model.livro.Livro;
import br.com.alura.literalura.model.respostaLivros.DadosRespostaLivros;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private List<Livro> livros = new ArrayList<>();
//    private List<Autor> autores = new ArrayList<>();
    ConverteDados conversor = new ConverteDados();
//    private ArrayList<DadosRespostaLivros> listaLivros =new ArrayList<>();

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            exibirOpcoesMenu();
            try {
                opcao = leitor.nextInt();
                leitor.nextLine();
                processarOpcao(opcao);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                leitor.nextLine();
            }
        }
    }

    private void exibirOpcoesMenu() {
        System.out.println("""
            \nEscolha o número de sua opção:
            1- Buscar livro pelo título
            2- Listar livros registrados
            3- Listar autores registrados
            4- Listar autores vivos em um determinado ano
            5- Listar livros em um determinado idioma
            0- Sair\n
            """);
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> buscarLivroPorTitulo();
            case 2 -> listarLivrosRegistrados();
            case 3 -> listarAutoresRegistrados();
            case 4 -> listarAutoresVivosNoAno();
            case 5 -> listarLivrosPorIdioma();
            case 0 -> System.out.println("\nEncerrando...\n");
            default -> System.out.println("Opção inválida! Selecione uma opção entre 1 e 5, ou 0 para Sair.");
        }
    }

    @Transactional
    private void buscarLivroPorTitulo() {
        System.out.println("\nDigite o nome do livro que deseja cadastrar: ");
        var nomeLivro = leitor.nextLine();

        var json = consumoApi.obterDados(ENDERECO + "/?search=" + nomeLivro.replace(" ", "%20"));

        try {
            DadosRespostaLivros resposta = conversor.obterDados(json, DadosRespostaLivros.class);

            if (resposta.results().isEmpty()) {
                System.out.println("\nNenhum livro encontrado com o título: " + nomeLivro);
                return;
            }

            DadosLivro livroBuscado = resposta.results().get(0);
            Livro livro = new Livro(livroBuscado);

            System.out.println(livro);

            System.out.println("""
                    \nDeseja cadastrar esse livro?
                    1- Sim
                    2- Não
                    """);
            var opcaoCadastrar = leitor.nextInt();
            leitor.nextLine();

            switch (opcaoCadastrar) {
                case 1 -> cadastrarLivro(livro);
                case 2 -> System.out.println("O livro \"" + livro.getTitulo() + "\" não foi cadastrado.");
                default -> System.out.println("Opção inválida! O livro \"" + livro.getTitulo() + "\" não foi cadastrado.");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao buscar o livro: " + e.getMessage());
        }
    }

    private void cadastrarLivro(Livro livro) {
        if (livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo()).isEmpty()) {
            livroRepository.save(livro);
            System.out.println("\nLivro cadastrado com sucesso.");
        } else {
            System.out.println("\nO livro \"" + livro.getTitulo() + "\" já está cadastrado.");
        }
    }

    @Transactional
    private void listarLivrosRegistrados() {
        livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro está cadastrado.");
            return;
        }

        System.out.println("\nLivros registrados: \n");
        livros.forEach(livro -> {
                Hibernate.initialize(livro.getAutores());
                Hibernate.initialize(livro.getIdioma());
                System.out.println(livro);
            }
        );
    }

    @Transactional
    private void listarAutoresRegistrados() {
        List<Autor> autoresRegistrados = autorRepository.findAllByOrderByNome();

        System.out.println("\nAutores registrados: \n");
        autoresRegistrados.forEach(System.out::println);
    }

    @Transactional
    private void listarAutoresVivosNoAno() {
        try {
            System.out.println("\nDigite o ano para listar os autores vivos: ");
            var ano = leitor.nextInt();
            leitor.nextLine();

//        List<Autor> autoresVivos = autorRepository.findAutoresVivosNoAno(ano);
            List<Autor> autoresVivos = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(ano, ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("\nNenhum autor cadastrado vivo no ano " + ano + ".\n");
                return;
            }

            System.out.println("\nAutores vivos no ano " + ano + ":\n");
            autoresVivos.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("\nEntrada inválida. Por favor, insira um número.\n");
            leitor.nextLine();
        }
    }

    @Transactional
    private void listarLivrosPorIdioma() {
        System.out.println("""
                \nSelecione o idioma:
                en- Inglês
                pt- Português\n
                """);
        var idioma = leitor.nextLine();

        if (!idioma.equalsIgnoreCase("en") && !idioma.equalsIgnoreCase("pt")) {
            System.out.println("\nOpção inválida! Selecione um idioma (en, pt).\n");
            return;
        }

        livros = livroRepository.findByIdioma(idioma);

        System.out.println("\nQuantidade de livros registrados em " + (idioma.equalsIgnoreCase("en") ? "inglês (en)" : "português (pt)") + ": " + livros.stream().count() + "\n");
        livros.forEach(System.out::println);
    }
}
