package com.alura.reserva_sala.domain.service;

import com.alura.reserva_sala.domain.model.Sala;
import com.alura.reserva_sala.domain.repository.SalaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    // Injeção de dependência via construtor (melhor prática)
    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    @Transactional
    public Sala salvar(Sala sala) {
        // Aqui você pode adicionar validações extras antes de persistir no MySQL
        return salaRepository.save(sala);
    }
}
