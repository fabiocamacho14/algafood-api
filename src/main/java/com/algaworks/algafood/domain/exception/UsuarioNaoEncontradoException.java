package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(Integer usuarioId) {
        this(String.format("Não existe cadastro de usuário com código %d", usuarioId));
    }
}
