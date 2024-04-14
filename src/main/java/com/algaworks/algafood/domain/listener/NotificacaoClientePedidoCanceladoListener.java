package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    @Qualifier("smtpEnvioEmailService")
    private EnvioEmailService envioEmailService;

    @EventListener
    public void aoCancelarPedido(PedidoCanceladoEvent pedidoConfirmadoEvent) {
        Pedido pedido = pedidoConfirmadoEvent.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto("Pedido cancelado - " + pedidoConfirmadoEvent.getPedido().getRestaurante().getNome())
                .destinatario(pedidoConfirmadoEvent.getPedido().getUsuario().getEmail())
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .build();

        System.out.println("LISTENER TA PEGANDO VIU");
        System.out.println("Este é um teste pra saber se o listener está pegando");
        envioEmailService.enviar(mensagem);
    }
}
