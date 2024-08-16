package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

    @Schema(name = "ID", description = "ID da permissão", example = "1")
    private Integer id;

    @Schema(name = "Nome", description = "Nome da permissão", example = "Global")
    private String nome;

    @Schema(name = "Descrição", description = "Descrição da permissão", example = "Todas as permissões administrativas")
    private String descricao;

    @Schema(hidden = true)
    private Links _links;
}
