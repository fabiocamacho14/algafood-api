package com.algaworks.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPutSenhaInput {

    @Schema(description = "Senha atual cadastrada", example = "admin123")
    @NotBlank
    private String senhaAtual;

    @Schema(description = "Senha nova a ser cadastrada", example = "root321")
    @NotBlank
    private String senhaNova;
}
