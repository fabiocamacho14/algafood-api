package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    @Qualifier("smtpEnvioEmailService")
    private EnvioEmailService envioEmailService;

//    @EventListener
    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent pedidoConfirmadoEvent) {
        Pedido pedido = pedidoConfirmadoEvent.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getUsuario().getEmail())
                .build();

        envioEmailService.enviar(mensagem);

    }
}
