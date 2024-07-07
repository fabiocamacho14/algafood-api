package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel {

    @Schema(description = "ID do Produto", example = "4")
    private Integer produtoId;

    @Schema(description = "Nome do Produto", example = "Luminária escura")
    private String produtoNome;

    @Schema(description = "Quantidade de unidades do pedido", example = "3")
    private Integer quantidade;

    @Schema(description = "Preço de uma unidade do produto", example = "10.45")
    private BigDecimal precoUnitario;

    @Schema(description = "Preço total do pedido", example = "31.35")
    private BigDecimal precoTotal;

    @Schema(description = "Observação do pedido", example = "Produto frágil")
    private String observacao;
}
