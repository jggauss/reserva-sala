package com.alura.reserva_sala.api.controller;

import com.alura.reserva_sala.domain.model.Sala;
import com.alura.reserva_sala.domain.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala criar(@RequestBody @Valid Sala sala) {
        return salaService.salvar(sala);
    }

    // Outros endpoints: buscar por id, atualizar e remover
}