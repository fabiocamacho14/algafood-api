package com.algaworks.algafood.api.model.view;

import com.algaworks.algafood.api.model.CozinhaModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Getter
@Setter
@Relation(collectionRelation = "restaurantes")
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel> {

    @Schema(name = "Id", description = "Código do restaurante", example = "1")
    private Integer id;

    @Schema(name = "Nome", description = "Nome do restaurante", example = "Quinoa Bar")
    private String nome;

    @Schema(name = "Taxa de frete", description = "Taxa de frete do restaurante", example = "7.85")
    private BigDecimal taxaFrete;

    @Schema(name = "Cozinha", description = "Cozinha do restaurante", example = "Peruana")
    private CozinhaModel cozinha;

    @Schema(hidden = true)
    private Links _links;
}
