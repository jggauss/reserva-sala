package com.alura.reserva_sala.domain.dto;


import java.time.LocalDateTime;

public record NovaReservaRequest(
        Long usuarioId,
        Long salaId,
        LocalDateTime inicio,
        LocalDateTime fim
) {
}
