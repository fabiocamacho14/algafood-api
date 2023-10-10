package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

//    public PedidoNaoEncontradoException(String message) {
//        super(message);
//    }

    public PedidoNaoEncontradoException(String pedidoId) {
        super(String.format("Pedido de código %s não encontrado.", pedidoId));
    }
}
