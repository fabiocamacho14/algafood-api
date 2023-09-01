package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String descricao;

    @PositiveOrZero
    @NotNull
    private BigDecimal preco;
}
