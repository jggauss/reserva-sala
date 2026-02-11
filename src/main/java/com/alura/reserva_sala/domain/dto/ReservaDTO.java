package com.alura.reserva_sala.domain.dto;

import com.alura.reserva_sala.domain.model.Reserva;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

public record ReservaDTO(
        Long id,
        LocalDateTime inicio,
        LocalDateTime fim,
        String status
) {
    // Você também pode criar um construtor que recebe a Entity
    public ReservaDTO(Reserva reserva) {
        this(
                reserva.getId(),
                reserva.getInicio(),
                reserva.getFim(),
                reserva.getStatus() != null ? reserva.getStatus().name() : "ATIVA"
        );
    }


}