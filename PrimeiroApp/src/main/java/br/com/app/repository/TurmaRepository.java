package br.com.app.repository;

//importação da classe Turma
import br.com.app.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//anotação @Repository para o spring fazer a configuração automatica do projeto
@Repository
/*
interface que extende JpaRepository para fazer a persistencia dos dados
persistencia de dados é a forma de salvar os dados no banco de dados
JpaRepository<Turma, String> é o tipo de dado que vai ser salvo e o tipo da chave primaria
Turma é o tipo de dado que vai ser salvo
String é o tipo da chave primaria
*/
public interface TurmaRepository extends JpaRepository<Turma, String> {


}
