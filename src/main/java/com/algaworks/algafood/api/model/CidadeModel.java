package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {

    @Schema(example = "12")
    private Integer id;

    @Schema(example = "Paranapiacaba")
    private String nome;

    private EstadoModel estado;

    @Schema(hidden = true)
    private Links _links;
}
