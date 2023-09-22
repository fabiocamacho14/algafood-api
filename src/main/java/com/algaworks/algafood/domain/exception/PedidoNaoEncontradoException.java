package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }

    public PedidoNaoEncontradoException(Integer pedidoId) {
        this(String.format("Pedido de código %d não encontrado.", pedidoId));
    }
}
