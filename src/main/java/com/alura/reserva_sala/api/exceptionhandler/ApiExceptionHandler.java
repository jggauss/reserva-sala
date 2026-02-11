package com.alura.reserva_sala.api.exceptionhandler;

import com.alura.reserva_sala.infra.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroResposta> handleBusiness(BusinessException ex) {
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(), OffsetDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}

// Classe auxiliar para o corpo da resposta
record ErroResposta(Integer status, OffsetDateTime dataHora, String mensagem) {
}