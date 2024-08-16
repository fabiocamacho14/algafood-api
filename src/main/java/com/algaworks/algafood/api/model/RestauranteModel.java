package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
//@Schema(name = "Restaurante", description = "Representa um restaurante")
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

    @Schema(description = "Id do restaurante")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Integer id;

    @Schema(description = "Nome do restaurante", example = "Restaurante da Dona Florinda")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @Schema(description = "Taxa de Frete do restaurante")
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @Schema(description = "Cozinha do restaurante")
    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;

    private EnderecoModel endereco;
    private Boolean ativo;
    private Boolean aberto;

    @Schema(hidden = true)
    private Links _links;
}
