package com.alura.reserva_sala.domain.service;

import com.alura.reserva_sala.domain.dto.NovaReservaRequest;
import com.alura.reserva_sala.domain.dto.ReservaDTO;
import com.alura.reserva_sala.domain.model.Reserva;
import com.alura.reserva_sala.domain.model.Sala;
import com.alura.reserva_sala.domain.model.StatusReserva;
import com.alura.reserva_sala.domain.model.Usuario;
import com.alura.reserva_sala.domain.repository.ReservaRepository;
import com.alura.reserva_sala.domain.repository.SalaRepository;
import com.alura.reserva_sala.domain.repository.UsuarioRepository;
import com.alura.reserva_sala.infra.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ReservaDTO criarReserva(NovaReservaRequest request) {
        // 1. Validações de existência
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        Sala sala = salaRepository.findById(request.salaId())
                .orElseThrow(() -> new BusinessException("Sala não encontrada."));

        if (!sala.isAtiva()) {
            throw new BusinessException("Não é possível reservar uma sala inativa.");
        }

        // 2. LEITURA PARA CONFLITO (Protegida pelo Isolation)
        boolean conflito = reservaRepository.existsConflito(
                sala.getId(), request.inicio(), request.fim());

        if (conflito) {
            throw new BusinessException("A sala já possui reserva para este período.");
        }

        // 3. GRAVAÇÃO (Atomicidade garantida)
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setSala(sala);
        reserva.setInicio(request.inicio());
        reserva.setFim(request.fim());
        reserva.setStatus(StatusReserva.ATIVA); // ADICIONE ESTA LINHA

        Reserva salva = reservaRepository.save(reserva);
        return new ReservaDTO(salva); // Use o construtor que criamos no DTO

        // Retorna o DTO em vez da Entity
    }
}