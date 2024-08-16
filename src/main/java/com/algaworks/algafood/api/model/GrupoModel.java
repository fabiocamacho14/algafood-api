package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
@Setter
@Getter
@Schema(name = "Grupo")
public class GrupoModel extends RepresentationModel<GrupoModel> {

    @Schema(name = "Id", description = "Código do grupo", example = "1")
    private Integer id;

    @Schema(name = "nome", description = "Nome do grupo", example = "Tenente")
    private String nome;

    @Schema(hidden = true)
    private Links _links;
}
