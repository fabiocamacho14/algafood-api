package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Integer estadoId) {
        this(String.format("Não existe cadastro de estado com código %d", estadoId));
    }
}
