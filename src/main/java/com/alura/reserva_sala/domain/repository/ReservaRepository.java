package com.alura.reserva_sala.domain.repository;

import com.alura.reserva_sala.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
            "WHERE r.sala.id = :salaId " +
            "AND r.status = 'ATIVA' " +
            "AND (:inicio < r.fim AND :fim > r.inicio)")
    boolean existsConflito(Long salaId, LocalDateTime inicio, LocalDateTime fim);
}
