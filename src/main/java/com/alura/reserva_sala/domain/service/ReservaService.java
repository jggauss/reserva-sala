package com.alura.reserva_sala.domain.service;

import com.alura.reserva_sala.domain.model.Reserva;
import com.alura.reserva_sala.domain.model.Sala;
import com.alura.reserva_sala.domain.model.Usuario;
import com.alura.reserva_sala.domain.repository.ReservaRepository;
import com.alura.reserva_sala.domain.repository.SalaRepository;
import com.alura.reserva_sala.domain.repository.UsuarioRepository;
import com.alura.reserva_sala.infra.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          SalaRepository salaRepository,
                          UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Reserva criarReserva(Reserva reserva) {
        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        Sala sala = salaRepository.findById(reserva.getSala().getId())
                .orElseThrow(() -> new BusinessException("Sala não encontrada."));

        if (!sala.isAtiva()) {
            throw new BusinessException("Não é possível reservar uma sala inativa.");
        }

        boolean conflito = reservaRepository.existsConflito(
                sala.getId(), reserva.getInicio(), reserva.getFim());

        if (conflito) {
            throw new BusinessException("A sala já possui reserva para este período.");
        }

        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Reserva não encontrada."));
        reserva.cancelar();
    }
}