package br.edu.ifsp.pep.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.pep.apirest.model.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

    public Optional<Aluno> findByMatricula(Integer matricula);
    
    
    
}
