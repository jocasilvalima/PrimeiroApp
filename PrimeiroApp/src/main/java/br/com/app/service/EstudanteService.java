package br.com.app.service;

// Importações necessárias
import br.com.app.entity.Estudante; // Importa a classe Estudante do pacote br.com.app.entity
import br.com.app.repository.EstudanteRepository; // Importa a classe EstudanteRepository do pacote br.com.app.repository
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired do Spring Framework
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service; // Importa a anotação Service do Spring Framework

import java.util.List;

   @Service // A anotação @Service indica que esta classe é um serviço gerenciado pelo Spring Framework.
   public class EstudanteService {

    @Autowired // A anotação @Autowired é usada para injetar automaticamente o bean EstudanteRepository neste campo.
    private EstudanteRepository estudanteRepo; // Campo autowired para o repositório EstudanteRepository

    // Método para salvar um Estudante
    public Estudante salvar(Estudante estudante){
        return estudanteRepo.save(estudante); // Salva a entidade Estudante usando o repositório
    }

    // Método para recuperar uma lista de todos os Estudantes
    public List<Estudante> buscarTodosEstudantes(){
        return estudanteRepo.findAll(); // Encontra e retorna todos os Estudantes usando o repositório
    }

    public Estudante buscarEstudantePeloId(Integer id){//método para buscar um estudante pelo id
        return estudanteRepo.findById(id).orElse(null);// Encontra e retorna o Estudante usando o repositório
    }

    // Método para deletar um Estudante pelo ID
    public Boolean deletarEstudante(Integer id){
        Estudante estudante = estudanteRepo.findById(id).orElse(null); // Encontra o Estudante pelo ID
        if(estudante != null){
            estudanteRepo.deleteById(id); // Deleta o Estudante pelo ID
            return true;
        }
        return false;
    }

    // Método para atualizar as informações de um Estudante
    public Estudante updateEstudante(Integer id, Estudante estudante){
        Estudante estudanteBD = estudanteRepo.findById(id).orElse(null);// Recupera um estudante do repositório com base no ID fornecido. Se nenhum estudante for encontrado, retorna null.
        if(estudanteBD != null){// Se o estudante for encontrado, atualize as informações do estudante
            estudanteBD.setNome(estudante.getNome());// Atualiza o nome do estudante com o nome fornecido no objeto "estudante".
            estudanteBD.setSobrenome(estudante.getSobrenome());// Atualiza o sobrenome do estudante com o sobrenome fornecido no objeto "estudante".
            estudanteBD.setEmail(estudante.getEmail());// Atualiza o email do estudante com o email fornecido no objeto "estudante".
            return estudanteRepo.save(estudanteBD); // Salva a entidade Estudante atualizada
        } else{// Se o estudante não for encontrado, retorne null
            return null; // Retorna null se o Estudante não for encontrado
        }
    }


    // Método para buscar um Estudante pelo ID
    public Page<Estudante> buscarEstudantesPorPaginacao(PageRequest page){
        return estudanteRepo.findAll(page);// Retorna uma lista de todos os estudantes do repositório com base na paginação especificada.
    }
}
