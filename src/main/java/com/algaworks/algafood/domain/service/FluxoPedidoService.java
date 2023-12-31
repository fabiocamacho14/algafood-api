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
    public void confirmar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);

        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);

        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);

        pedido.cancelar();
    }
}
