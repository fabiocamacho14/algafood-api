package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Integer pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatusPedido().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Status de pedido %d não pode ser alterado de %s para %s",
                    pedidoId,
                    pedido.getStatusPedido().getDescricao(),
                    StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void entregar(Integer pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatusPedido().equals(StatusPedido.CONFIRMADO)) {
            throw new NegocioException(String.format("Status de pedido %d não pode ser alterado de %s para %s",
                    pedidoId,
                    pedido.getStatusPedido().getDescricao(),
                    StatusPedido.ENTREGUE.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void cancelar(Integer pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatusPedido().equals(StatusPedido.CONFIRMADO)) {
            throw new NegocioException(String.format("Status de pedido %d não pode ser alterado de %s para %s",
                    pedidoId,
                    pedido.getStatusPedido().getDescricao(),
                    StatusPedido.CANCELADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
