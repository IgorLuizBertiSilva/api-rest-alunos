package br.edu.ifsp.pep.apirest.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Aluno implements Serializable{
    @Id
    private Integer matricula;
    private String nome;
    private String cpf;
    
}
