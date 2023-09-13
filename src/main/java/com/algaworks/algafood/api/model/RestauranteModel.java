package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Integer id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private EnderecoModel endereco;
    private Boolean ativo;
    private Boolean aberto;
}
