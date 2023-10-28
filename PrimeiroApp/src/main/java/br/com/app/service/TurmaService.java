package br.com.app.service;


import br.com.app.entity.Turma;
import br.com.app.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepo;


    public Turma salvar(Turma turma){
        return turmaRepo.save(turma);
    }

    public List<Turma> buscarTodasTurmas(){
        return turmaRepo.findAll();
    }

    public Turma buscarTurmaPorSala(String sala){
        return turmaRepo.findById(sala).orElse(null);
    }

    public Boolean deletarTurmaPorSala(String sala){
        Turma turma = turmaRepo.findById(sala).orElse(null);
        if(turma != null){
            turmaRepo.deleteAllById(sala);
            return true;
        }
        return false;
    }

    public Turma updateTurma(String sala, Turma turma){
        Turma turmaBD = turmaRepo.findById(sala).orElse(null);
        if(turmaBD != null){
            turmaBD.setNome(turma.getNome());
            turmaBD.setTurno(turma.getTurno());
            turmaBD.setCurso(turma.getCurso());
            return turmaRepo.save(turmaBD);
        } else{
            return null;
        }
    }

    public Page<Turma> buscarTurmasPorPaginacao(PageRequest page){
        return turmaRepo.findAll(page);
    }

}
