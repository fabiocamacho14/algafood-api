package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel> {

    @Schema(description = "Código do restaurante", example = "1")
    private Integer id;

    @Schema(description = "Nome do restaurante", example = "Toldo verde")
    private String nome;

    @Schema(hidden = true)
    private Links _links;
}
