package br.com.app.entity;

//impotação das bibliotecas
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//anotação para o banco de dados fazendo referencia a tabela professor para fazer a criação da tabela
@Entity
//anotação para o lombok fazer a criação dos metodos get, set, construtor, toString, equals e hashcode

@Data
//classe professor
public class Professor {

    //anotação para o banco de dados id fazendo referencia a chave primaria
    @Id

    //anotaçao para o banco de dados gerar o id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //atributos da classe professor
    private Integer matricula;
    private String nome;
    private String especializacao;
    private String telefone;

}
