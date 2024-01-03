package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Transactional
    public Pedido adicionar(Pedido pedido) {
//        Gerenciar entidades

        Integer restauranteId = pedido.getRestaurante().getId();
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        Integer formaPagamentoId = pedido.getFormaPagamento().getId();
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        Integer cidadeId = pedido.getEnderecoEntrega().getCidade().getId();
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        List<ItemPedido> itens = pedido.getItens().stream()
                .map(itemPedido -> {
                    Produto produto = cadastroProdutoService.buscarOuFalhar(itemPedido.getProduto().getId());
                    itemPedido.setProduto(produto);
                    itemPedido.setPrecoUnitario(produto.getPreco());
                    return itemPedido;
                }).toList();

        pedido.setItens(itens);
        pedido.setRestaurante(restaurante);
        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setStatusPedido(StatusPedido.CRIADO);
        pedido.setFormaPagamento(formaPagamento);
//        pedido.getRestaurante().setTaxaFrete(restaurante.getTaxaFrete()); TODO arruma isso aqui
//        Cliente padrão por enquanto
        pedido.setUsuario(cadastroUsuarioService.buscarOuFalhar(1));


        if (!restaurante.verificarSeContemFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Restaurante de código %d não aceita forma de pagamento de código %d",
                    restaurante.getId(), formaPagamento.getId()));
        }

        for (ItemPedido itemPedido: pedido.getItens()) {
            Produto produto = cadastroProdutoService.buscarOuFalhar(itemPedido.getProduto().getId());
            if (!produto.getRestaurante().equals(restaurante)) {
                throw new NegocioException((String.format("Produto de código %d não pertence ao restaurante de código %d",
                        produto.getId(), restaurante.getId())));
            }
        }

        pedido.calcularPedido();


        return pedidoRepository.save(pedido);
    }

    public Pedido buscarOuFalhar(String codigo) {
        return pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }
}
