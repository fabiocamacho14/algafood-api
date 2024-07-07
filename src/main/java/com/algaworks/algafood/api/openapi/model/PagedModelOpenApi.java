package com.algaworks.algafood.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedModelOpenApi<T> {

    private List<T> content;

    @Schema(example = "10", description = "Quantidade de registros por página")
    private Long size;

    @Schema(example = "50", description = "Total de registros")
    private Long totalElements;

    @Schema(example = "5", description = "Total de páginas")
    private Long totalPages;

    @Schema(example = "0", description = "Número da página atual (começa em 0)")
    private Long number;
}
