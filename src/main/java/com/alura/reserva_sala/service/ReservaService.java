package com.alura.reserva_sala.service;

import com.alura.reserva_sala.domain.model.Reserva;
import com.alura.reserva_sala.domain.repository.ReservaRepository;
import com.alura.reserva_sala.infra.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Transactional
    public Reserva criarReserva(Reserva novaReserva) {
        // Regra: Não reservar sala inativa
        if (!novaReserva.getSala().isAtiva()) {
            throw new BusinessException("Sala está inativa para novas reservas.");
        }

        // Regra de Não Sobreposição (Intervalo Semiaberto)
        boolean ocupada = repository.existsConflito(
                novaReserva.getSala().getId(),
                novaReserva.getInicio(),
                novaReserva.getFim()
        );

        if (ocupada) {
            throw new BusinessException("A sala já possui uma reserva ativa neste horário.");
        }

        return repository.save(novaReserva);
    }
}