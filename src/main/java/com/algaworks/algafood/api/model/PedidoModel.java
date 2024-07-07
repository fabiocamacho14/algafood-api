package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    @Schema(description = "Código do pedido", example = "20e58bc5-3675-11ef-b0a5-025072131a20")
    private String codigo;

    @Schema(description = "Subtotal do pedido", example = "10.50")
    private BigDecimal subtotal;

    @Schema(description = "Frete do pedido", example = "10.50")
    private BigDecimal taxaFrete;

    @Schema(description = "Valor total do pedido", example = "10.50")
    private BigDecimal valorTotal;

    @Schema(description = "Status atual do pedido", example = "CONFIRMADO")
    private String status;

    @Schema(description = "Data de criação do pedido", example = "2024-06-30T00:06:41Z")
    private OffsetDateTime dataCriacao;

    @Schema(description = "Data de confirmação do pedido", example = "2024-06-30T00:06:41Z")
    private OffsetDateTime dataConfirmacao;

    @Schema(description = "Data de entrega do pedido", example = "2024-06-30T00:06:41Z")
    private OffsetDateTime dataEntrega;

    @Schema(description = "Data de cancelamento do pedido", example = "2024-06-30T00:06:41Z")
    private OffsetDateTime dataCancelamento;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel endereco;
    private List<ItemPedidoModel> itens;
}
