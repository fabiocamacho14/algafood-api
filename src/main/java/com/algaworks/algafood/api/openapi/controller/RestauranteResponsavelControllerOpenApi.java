package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de Responsáveis de restaurantes", description = "Controlador responsável pelas ações de " +
        "usuários responsáveis de um restaurante específico")
public interface RestauranteResponsavelControllerOpenApi {

    @Operation(summary = "Lista todos os responsáveis", description = "Lista todos os usuários responsáveis pelo " +
            "restaurante",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/responsaveis",
                            parameters = {
                                @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}")
                            }
                        ),
                        @Link(name = "associar", description = "Associa um usuário como responsável",
                                operationRef = "https://localhost:8080/restaurantes/{usuarioId}/responsaveis" +
                                        "/{usuarioId}",
                                parameters = {
                                        @LinkParameter(name = "Código de usuário", expression = "{usuarioId}"),
                                        @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}")
                                }
                        )
                })
            }
    )
    CollectionModel<UsuarioModel> listar(@PathVariable Integer restauranteId);

    @Operation(summary = "Associa um usuário como responsável de um restaurante", description = "Associa um usuário " +
            "de código específico como um dos responsáveis de um restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    ResponseEntity<Void> associarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId);

    @Operation(summary = "Desassocia um usuário como responsável de um restaurante", description = "Associa um " +
            "usuário de código específico como um dos responsáveis de um restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    ResponseEntity<Void> desassociarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId);
}
