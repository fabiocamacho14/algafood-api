package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEncontradoException(String message) {
        super(message);
    }

    public GrupoNaoEncontradoException(Integer grupoId) {
        this(String.format("Não existe cadastro de grupo com código %d", grupoId));
    }
}
