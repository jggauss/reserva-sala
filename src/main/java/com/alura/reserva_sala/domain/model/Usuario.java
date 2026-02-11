package com.alura.reserva_sala.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {
    // Identificador único para persistência em banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome para identificação humana e relatórios
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    // Construtor padrão exigido pelo JPA
    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
