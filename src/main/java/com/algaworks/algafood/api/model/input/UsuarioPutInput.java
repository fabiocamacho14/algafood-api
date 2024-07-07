package com.algaworks.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPutInput {

    @NotBlank
    @Schema(description = "Nome do usuário", example = "Carlos da Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "carlinhossilva@hotmail.com")
    @Email
    @NotBlank
    private String email;
}
