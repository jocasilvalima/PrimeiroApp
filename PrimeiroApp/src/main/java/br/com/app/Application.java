package br.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//anotação SpringBootApplication para o spring fazer a configuração automatica do projeto
@SpringBootApplication
//classe principal
public class Application {

	//metodo principal para executar o projeto
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
