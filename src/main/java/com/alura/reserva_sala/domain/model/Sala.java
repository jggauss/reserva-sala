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
    private int capacidade;

    // Define se a sala está disponível para novas reservas (manutenção, etc.)
    private boolean ativa = true;


    public Sala(Long id, String nome, int capacidade, boolean ativa) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ativa = ativa;
    }

    // O método isAtiva é o "getter" para campos booleanos
    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Long getId() {
        return id;
    }
}
