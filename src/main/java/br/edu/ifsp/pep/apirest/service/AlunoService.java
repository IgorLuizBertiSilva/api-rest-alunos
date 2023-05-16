package br.edu.ifsp.pep.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.pep.apirest.model.Aluno;
import br.edu.ifsp.pep.apirest.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    AlunoRepository alunoRepository;

    public void insert(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findByMatricula(Integer matricula){
        return alunoRepository.findByMatricula(matricula);
    }

    public Integer deleteById(Integer matricula){
        Optional<Aluno> alunoOptional = findByMatricula(matricula);

        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            alunoRepository.delete(aluno);
            return 1;
        }
        return 0;
    }

    public Aluno update(Aluno alunoNovoDados, Integer matricula){
        Optional<Aluno> alunoOptional = findByMatricula(matricula);

        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            aluno.setNome(alunoNovoDados.getNome());
            aluno.setCpf(alunoNovoDados.getCpf());
            alunoRepository.save(aluno);
            return aluno;
        }
        
        return null;
    }

}