package com.alura.reserva_sala.api.controller;

import com.alura.reserva_sala.domain.model.Usuario;
import com.alura.reserva_sala.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Construtor com o nome exato da classe para evitar erros de compilação
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.salvar(usuario);
    }
}