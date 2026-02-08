package com.alura.reserva_sala.infra.exception;


// Estendemos RuntimeException para que o Spring consiga gerenciar o erro
public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}