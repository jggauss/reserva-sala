package com.alura.reserva_sala.api.controller;

import com.alura.reserva_sala.domain.dto.NovaReservaRequest;
import com.alura.reserva_sala.domain.dto.ReservaDTO;
import com.alura.reserva_sala.domain.model.Reserva;
import com.alura.reserva_sala.domain.service.ReservaService;
import com.alura.reserva_sala.domain.repository.ReservaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ReservaDTO criar(@RequestBody @Valid NovaReservaRequest request) {
        // Note que agora recebemos 'request' (o DTO) e não mais a Entidade 'reserva'
        return reservaService.criarReserva(request);
    }

    @GetMapping("/usuario/{usuarioId}")
    public Page<Reserva> listarPorUsuario(@PathVariable Long usuarioId, Pageable paginacao) {
        // Agora o tipo de retorno (Page) coincide com o que o Repository devolve
        return reservaRepository.findByUsuarioId(usuarioId, paginacao);
    }
}