package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPutSenhaInput {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String senhaNova;
}
