package br.com.app.entity;

 //impotação das bibliotecas
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

//anotação para o banco de dados fazendo referencia a tabela turma para fazer a criação da tabela
@Entity

//anotação para o lombok fazer a criação dos metodos get e set
@Data

//classe turma
public class Turma {

    //anição para o banco de dados id fazendo referencia a chave primaria
    @Id

    //atributos da classe turma
    private String nome;
    private String turno;
    private String curso;

}