package com.algaworks.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    @Valid
    private Integer produtoId; //TODO arrumar a mensagem de erro disso aqui

    @NotNull
    @Positive
    private Integer quantidade;

    private String observacao;
}
