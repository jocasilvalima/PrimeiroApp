package br.com.app.entity;

//impotação das bibliotecas
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
//importação localdate para fazer a data de nascimento
import java.time.LocalDate;

//anotação para o banco de dados fazendo referencia a tabela estudante para fazer a criação da tabela
@Entity
//anotação para o lombok fazer a criação dos metodos get e set
@Data
//classe estudante
public class Estudante {

    //anotação para o banco de dados id fazendo referencia a chave primaria
    @Id
    //anotaçao para o banco de dados gerar o id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //atributos da classe estudante
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private LocalDate dataNascimento;
}
