package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

    @Schema(description = "Código da forma de pagamento", example = "1")
    private Integer id;

    @Schema(description = "Descrição da forma de pagamento", example = "Cartão de débito")
    private String descricao;
}
