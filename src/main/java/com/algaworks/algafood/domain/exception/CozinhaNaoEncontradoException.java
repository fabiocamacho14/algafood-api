package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradoException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradoException(Integer cozinhaId) {
        this(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
    }
}
