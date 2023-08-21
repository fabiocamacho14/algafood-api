package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(String message) {
        super(message);
    }

    public FormaPagamentoNaoEncontradaException(Integer formaPagamentoId) {
        this(String.format("Não existe cadastro de forma de pagamento com o código %d", formaPagamentoId));
    }
}
