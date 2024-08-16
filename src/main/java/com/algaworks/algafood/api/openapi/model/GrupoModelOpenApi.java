package com.algaworks.algafood.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

@Getter
@Setter
@Tag(name = "GrupoModel")
public class GrupoModelOpenApi {

    @Schema(name = "Id", description = "Código do grupo", example = "1")
    private Integer id;

    @Schema(name = "nome", description = "Nome do grupo", example = "Tenente")
    private String nome;

    @Schema(name = "links", description = "Links de HATEOAS")
    private List<Link> links;
}
