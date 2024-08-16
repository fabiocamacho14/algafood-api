package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {

    @Schema(name = "ID", description = "Código do estado", example = "1")
    private Integer id;

    @Schema(name = "Nome", description = "Nome do estado", example = "Santa Catarina")
    private String nome;

    @Schema(hidden = true)
    private Links _links;
}
