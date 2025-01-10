package br.com.alura.literalura;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.ExibeMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Principal menu = new Principal();
//		menu.exibeMenu();

		var consumoApi = new ConsumoApi();

//		var json = consumoApi.obterDados("https://gutendex.com/books?search=tempest");
		var json = consumoApi.obterDados("https://gutendex.com/books/");
//		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");

			System.out.println("json: " + json);
//		ConverteDados conversor = new ConverteDados();
//		DadosLivro dados = conversor.obterDados(json, DadosLivro.class);
//		System.out.println("dados: " + dados.titulo());
	}
}
