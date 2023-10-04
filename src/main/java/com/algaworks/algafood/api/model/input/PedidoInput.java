package com.algaworks.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @NotNull
    @Valid
    private RestauranteIdInput restaurante;

    @NotNull
    @Valid
    private FormaPagamentoIdInput formaPagamento;

    @NotNull
    @Valid
    private EnderecoInput enderecoEntrega;

    @NotNull
    @NotEmpty
    @Valid
    private List<ItemPedidoInput> itens;
}
