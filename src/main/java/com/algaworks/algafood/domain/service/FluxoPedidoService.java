package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.infrastructure.service.email.SandboxEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

//    @Qualifier("fakeEnvioEmailService")
//    @Autowired
//    private EnvioEmailService envioEmailService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
        pedidoRepository.save(pedido);

//        var mensagem = EnvioEmailService.Mensagem.builder()
//                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
//                .corpo("pedido-confirmado.html")
//                .variavel("pedido", pedido)
//                .destinatario(pedido.getUsuario().getEmail())
//                .build();
//
//        envioEmailService.enviar(mensagem, pedido);
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
        pedidoRepository.delete(pedido);
    }
}
