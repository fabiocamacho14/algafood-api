package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.*;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(algaLinks.linkToPedidos());

        if (pedido.podeSerConfirmado()) {
            pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoModel.add(algaLinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar"));
        }

        RestauranteResumoModel restaurante = pedidoModel.getRestaurante();
        restaurante.add(algaLinks.linkToRestaurantes(restaurante.getId()));

        UsuarioModel usuario = pedidoModel.getCliente();
        usuario.add(algaLinks.linkToUsuarios(usuario.getId()));

        FormaPagamentoModel formaPagamento = pedidoModel.getFormaPagamento();
        formaPagamento.add(algaLinks.linkToFormaPagamentos(formaPagamento.getId()));

        CidadeResumoModel cidade = pedidoModel.getEndereco().getCidade();
        cidade.add(algaLinks.linkToCidades(cidade.getId()));

        List<ItemPedidoModel> itens = pedidoModel.getItens();
        itens.forEach(item -> {
            item.add(algaLinks.linkToProdutos(restaurante.getId(), item.getProdutoId()));
        });

        pedidoModel.add(algaLinks.linkToPedidosCol());

        return pedidoModel;
    }

    @Override
    public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities);
//                .add(linkTo(PedidoController.class)
//                        .withRel(IanaLinkRelations.COLLECTION));
    }
}
