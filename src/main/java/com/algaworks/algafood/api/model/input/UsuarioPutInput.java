package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPutInput {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;
}
