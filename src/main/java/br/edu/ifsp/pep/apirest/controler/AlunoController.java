package br.edu.ifsp.pep.apirest.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.pep.apirest.model.Aluno;
import br.edu.ifsp.pep.apirest.service.AlunoService;

@RestController
@RequestMapping("/api")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;
    
    private List<Aluno> alunos;
    
    @PostMapping("/inserir")
    public ResponseEntity<Aluno> insert(@RequestBody Aluno aluno){
        alunoService.insert(aluno);



        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping("/achar/todos")
    public List<Aluno> findAll(){
        if(alunos == null){
            alunos = alunoService.findAll();
        }
        return alunos;
    }

    @PutMapping("/atualizar/{matricula}")
    public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Integer matricula){
        Optional<Aluno> alunoOptional = alunoService.findByMatricula(matricula);

        // Aluno não achado
        if(alunoOptional.isEmpty()) return ResponseEntity.notFound().build();
        
        // Aluno achado
        Aluno alunoAtualizado = alunoService.update(aluno, matricula);

        // Conseguiu atualizar
        if(alunoAtualizado == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(alunoAtualizado);
    }

    @PatchMapping("/atualizar/{matricula}")
    public ResponseEntity<Aluno> updateCPF(@RequestParam("CPF") String cpf, @PathVariable Integer matricula){
        Optional<Aluno> alunoOptional = alunoService.findByMatricula(matricula);

        // Aluno não achado
        if(alunoOptional.isEmpty()) return ResponseEntity.notFound().build();
        
        Aluno aluno = alunoOptional.get();

        aluno.setCpf(cpf);

        // Aluno achado
        Aluno alunoAtualizado = alunoService.update(aluno, matricula);

        // Conseguiu atualizar
        if(alunoAtualizado == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(alunoAtualizado);
    }

    @DeleteMapping("/deletar/{matricula}")
    public ResponseEntity<Integer> deleteById(@PathVariable Integer matricula){
        Optional<Aluno> alunoOptional = alunoService.findByMatricula(matricula);

        // Aluno existe
        if(alunoOptional.isEmpty()) return ResponseEntity.notFound().build();
        
        Integer resultado = alunoService.deleteById(matricula);

        if(resultado == 0) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(null);
    }


}
