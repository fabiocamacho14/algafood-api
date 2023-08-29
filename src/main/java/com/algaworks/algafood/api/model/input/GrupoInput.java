package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class GrupoInput {

    @NotBlank
    private String nome;
}
