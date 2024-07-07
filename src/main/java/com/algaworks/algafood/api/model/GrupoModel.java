package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {

    @Schema(name = "nome", description = "Nome do grupo", example = "Tenente")
    private String nome;


}
