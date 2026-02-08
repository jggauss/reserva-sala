package com.alura.reserva_sala.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "O usuário é obrigatório")
    private Usuario usuario; // Referência ao dono da reserva

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @NotNull(message = "A sala é obrigatória")
    private Sala sala;       // Referência à sala reservada

    @Column(nullable = false)
    @FutureOrPresent(message = "O início não pode ser no passado")
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    public Reserva() {}

    public Reserva(Usuario usuario, Sala sala, LocalDateTime inicio, LocalDateTime fim) {
        validarDatas(inicio, fim);
        validarSalaAtiva(sala);

        this.usuario = usuario;
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
        this.status = StatusReserva.ATIVA;
    }

    // Método para validar o período cronológico
    public boolean isPeriodoValido() {
        return inicio != null && fim != null && inicio.isBefore(fim);
    }

    private void validarDatas(LocalDateTime inicio, LocalDateTime fim) {
        // Garante que o período faça sentido cronológico
        if (inicio == null || fim == null || !inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Data de início deve ser anterior ao fim.");
        }
    }

    private void validarSalaAtiva(Sala sala) {
        // Bloqueia reservas em salas inativas diretamente no domínio
        if (!sala.isAtiva()) {
            throw new IllegalStateException("Não é possível reservar uma sala inativa.");
        }
    }

    public void cancelar() {
        this.status = StatusReserva.CANCELADA;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public StatusReserva getStatus() {
        return status;
    }}
