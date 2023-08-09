package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Integer restauranteId) {
        this(String.format("Não existe cadastro de resturante com código %d", restauranteId));
    }
}
