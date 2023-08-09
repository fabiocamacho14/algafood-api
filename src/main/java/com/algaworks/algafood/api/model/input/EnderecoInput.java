package com.algaworks.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    @NotNull
    @Pattern(regexp = "(^[0-9]{5})-?([0-9]{3}$)")
    private String cep;

    @NotNull
    private String logradouro;

    @NotNull
    @PositiveOrZero
    private String numero;

    private String complemento;

    @NotNull
    private String bairro;

    @NotNull
    @Valid
    private CidadeIdInput cidade;
}
