package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradoException(String message) {
        super(message);
    }

    public CidadeNaoEncontradoException(Integer cidadeId) {
        this(String.format("Não existe cadastro de cidade com código %d", cidadeId));
    }
}
