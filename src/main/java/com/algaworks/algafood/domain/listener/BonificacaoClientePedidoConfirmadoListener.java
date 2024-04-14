package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonificacaoClientePedidoConfirmadoListener {

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent pedidoConfirmadoEvent) {
        System.out.println("Calculando pontos para cliente: " + pedidoConfirmadoEvent.getPedido().getUsuario().getNome());
    }
}
