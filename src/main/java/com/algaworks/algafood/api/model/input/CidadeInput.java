package com.algaworks.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

    @Schema(example = "Sorocaba", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String nome;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Valid
    @NotNull
    private EstadoIdInput estado;
}
