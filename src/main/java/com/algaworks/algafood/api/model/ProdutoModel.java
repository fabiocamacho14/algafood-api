package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    @Schema(description = "Código do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do produto", example = "Abajur 2D")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Um lindo abajur que ilumina todo seu cômodo")
    private String descricao;

    @Schema(description = "Preço do produto", example = "38.20")
    private BigDecimal preco;

    @Schema(description = "Se restaurante está ativo", example = "true")
    private Boolean ativo;
}
