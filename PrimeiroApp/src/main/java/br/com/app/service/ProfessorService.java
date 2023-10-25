package br.com.app.service;
// Importações necessárias

import br.com.app.entity.Professor;// Importa a classe Professor do pacote br.com.app.entity
import br.com.app.repository.ProfessorRepository;// Importa a classe ProfessorRepository do pacote br.com.app.repository
import org.springframework.beans.factory.annotation.Autowired;// Importa a anotação Autowired do Spring Framework
import org.springframework.data.domain.Page;// Importa a classe Page do Spring Framework
import org.springframework.data.domain.PageRequest;// Importa a classe PageRequest do Spring Framework
import org.springframework.stereotype.Service;// Importa a anotação Service do Spring Framework

import java.util.List;// Importa a classe List do Java

@Service// A anotação @Service indica que esta classe é um serviço gerenciado pelo Spring Framework.
//classe de serviço para o Professor
public class ProfessorService {

    @Autowired// A anotação @Autowired é usada para injetar automaticamente o bean ProfessorRepository neste campo.
    private ProfessorRepository professorRepo;//pega o repositório do professor para usar os métodos do crud


    public Professor salvar(Professor professor){//método para salvar um professor
        return professorRepo.save(professor);// Salva a entidade Professor usando o repositório
    }

    public List<Professor> buscarTodosProfessores(){//método para buscar todos os professores
        return professorRepo.findAll();// Encontra e retorna todos os Professores usando o repositório
    }


    public Professor buscarProfessorPorMatricula(Integer matricula){//método para buscar um professor pela matricula
        return professorRepo.findById(matricula).orElse(null);// Encontra e retorna o Professor usando o repositório
    }

    public Boolean deletarProfessor(Integer matricula){//método para deletar um professor pela matricula
        Professor professor = professorRepo.findById(matricula).orElse(null);// Encontra o Professor pelo matricula se não existir retorna null
        if(professor != null){//se o professor existir faz o delete
            professorRepo.deleteAllById(matricula);// Deleta o Professor pela matricula
            return true;//retorna true se o professor for deletado
        }
        return false;//retorna false se o professor não for deletado
    }

    public Professor updateProfessor(Integer matricula, Professor professor){//método para atualizar um professor
        Professor professorBD = professorRepo.findById(matricula).orElse(null);// Recupera um professor do repositório com base no ID fornecido. Se nenhum professor for encontrado, retorna null.
        if(professorBD != null){// Se o professor for encontrado, atualize as informações do professor
            professorBD.setNomecompleto(professor.getNomecompleto());// Atualiza o nome completo do professor com o nome fornecido no objeto "professor".
            professorBD.setEspecializacao(professor.getEspecializacao());// Atualiza o especialização do professor com o sobrenome fornecido no objeto "professor".
            professorBD.setTelefone(professor.getTelefone());// Atualiza o telefone do professor com o email fornecido no objeto "professor".
            return professorRepo.save(professorBD); // Salva a entidade Professor atualizada
        } else{// Se o professor não for encontrado, retorne null
            return null; // Retorna null se o Professor não for encontrado
        }
    }

    public Page<Professor> buscarProfessoresPorPaginacao(PageRequest page){//método para buscar professores por paginação
        return professorRepo.findAll(page);// Retorna uma lista de todos os professores do repositório com base na paginação especificada.
    }

}
