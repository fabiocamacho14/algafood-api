package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @Schema(description = "Código do usuário", example = "1")
    private Integer id;

    @Schema(description = "Nome do usuário", example = "Carlos da Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "carlinhossilva@hotmail.com")
    private String email;
}
