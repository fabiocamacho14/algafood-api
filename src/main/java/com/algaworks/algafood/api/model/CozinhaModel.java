package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

    @JsonView(RestauranteView.Resumo.class)
    @Schema(example = "1", description = "ID da cozinha")
    private Integer id;

    @JsonView(RestauranteView.Resumo.class)
    @Schema(example = "Toldo Verde", description = "Nome do restaurante")
    private String nome;
}
