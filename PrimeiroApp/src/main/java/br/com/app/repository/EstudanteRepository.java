package br.com.app.repository;

//importação da classe Turma
import br.com.app.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//anotação @Repository para o spring fazer a configuração automatica do projeto
@Repository
/*
interface que extende JpaRepository para fazer a persistencia dos dados
persistencia de dados é a forma de salvar os dados no banco de dados
JpaRepository<Estudante, Integer> é o tipo de dado que vai ser salvo e o tipo da chave primaria
Estudante é o tipo de dado que vai ser salvo
Integer é o tipo da chave primaria
*/
public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {

}