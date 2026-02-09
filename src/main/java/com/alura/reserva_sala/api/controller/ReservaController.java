package com.alura.reserva_sala.api.controller;

import com.alura.reserva_sala.domain.model.Reserva;
import com.alura.reserva_sala.domain.service.ReservaService;
import com.alura.reserva_sala.domain.repository.ReservaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaRepository reservaRepository; // DECLARAÇÃO DA VARIÁVEL

    // O CONSTRUTOR PRECISA RECEBER OS DOIS PARA O SPRING INJETAR
    public ReservaController(ReservaService reservaService, ReservaRepository reservaRepository) {
        this.reservaService = reservaService;
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva criar(@RequestBody @Valid Reserva reserva) {
        return reservaService.criarReserva(reserva);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> listarPorUsuario(@PathVariable Long usuarioId) {
        // AGORA O JAVA RECONHECE O OBJETO reservaRepository
        return reservaRepository.findByUsuarioId(usuarioId);
    }
}