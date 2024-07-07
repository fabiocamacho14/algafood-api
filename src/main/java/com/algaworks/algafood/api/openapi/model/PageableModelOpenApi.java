package com.algaworks.algafood.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Tag(name = "Pageable")
public class PageableModelOpenApi {

    @Schema(example = "2", description = "Número da página (começa em 0)")
    private int page;

    @Schema(example = "4", description = "Quantidade de elementos por página")
    private int size;

    @Schema(example = "nome,asc", description = "Nome da propriedade para ordenação")
    private List<String> sort;
}
