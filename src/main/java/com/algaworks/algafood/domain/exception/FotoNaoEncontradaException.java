package com.algaworks.algafood.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FotoNaoEncontradaException(String message) {
        super(message);
    }

    public FotoNaoEncontradaException(Integer restauranteId, Integer produtoId) {
        this(String.format("Foto do produto de id %d, cadastrado no restaurante %d não foi encontrada.", produtoId, restauranteId));
    }
}
