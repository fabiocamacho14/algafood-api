package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

    @Schema(description = "Código da cidade", example = "14")
    private Integer id;

    @Schema(description = "Nome da cidade", example = "Santo Antônio de Posse")
    private String nome;

    @Schema(description = "Estado da cidade", example = "São Paulo")
    private String estado;
}
