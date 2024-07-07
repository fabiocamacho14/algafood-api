package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public interface RestauranteResponsavelControllerOpenApi {

    Collection<UsuarioModel> listar(@PathVariable Integer restauranteId);

    @Operation(summary = "Associa um usuário como responsável de um restaurante", description = "Associa um usuário " +
            "de código específico como um dos responsáveis de um restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    void associarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId);

    @Operation(summary = "Desassocia um usuário como responsável de um restaurante", description = "Associa um " +
            "usuário de código específico como um dos responsáveis de um restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    void desassociarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId);
}
