package com.alura.reserva_sala.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    // Capacidade positiva para garantir que a sala suporte reuniões
    @Positive(message = "A capacidade deve ser positiva")
    private Integer capacidade;

    // Define se a sala está disponível para novas reservas (manutenção, etc.)
    private Boolean ativa = true;

    // CONSTRUTOR VAZIO OBRIGATÓRIO PARA O HIBERNATE

    public Sala() {
    }

    public Sala(Long id, String nome, Integer capacidade, Boolean ativa) {

        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ativa = ativa;
    }

    // O método isAtiva é o "getter" para campos booleanos
    public Boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Long getId() {
        return id;
    }
}
