package com.alura.reserva_sala.domain.repository;



import com.alura.reserva_sala.domain.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    // Ao estender JpaRepository, ganhamos métodos como save(), findAll() e findById()
}
