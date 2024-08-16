package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Controlador de permissões", description = "Controlador responsável pelo gerenciamento de permissões do " +
        "sistema.")
public interface PermissaoControllerOpenApi {

    @Operation(summary = "Lista todas as permissões", description = "Lista todas as permissões cadastradas no banco " +
            "de dados do sistema",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = PermissaoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Collection",
                            operationRef = "https://localhost:8080/permissoes"
                        )
                }),

            }
    )
    CollectionModel<PermissaoModel> listar();

    @Operation(summary = "Busca uma permissão", description = "Busca uma permissão de código específico cadastrada no" +
            " banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Permissão não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de permissão inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "200", description = "Permissão encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = PermissaoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/permissoes/{permissaoId}",
                                    parameters = {
                                        @LinkParameter(name = "Código de permissão", expression = "{permissaoId}")
                                    }
                            ),
                            @Link(name = "collection", description = "Collection Link",
                                    operationRef = "https://localhost:8080/permissoes"
                            )
                    })
            }
    )
    PermissaoModel buscar(@PathVariable Integer permissaoId);
}
