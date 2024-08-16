package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de estados", description = "Controlador responsável pelo gerenciamento de estados " +
        "cadastrados no banco de dados do sistema.")
public interface EstadoControllerOpenApi {

    @Operation(summary = "Lista todos os estados", description = "Lista todos os estados cadastrados no banco de " +
            "dados do sistema.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = EstadoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/estados"
                        )
                })
            }
    )
    CollectionModel<EstadoModel> listar();

    @Operation(summary = "Busca um estado", description = "Busca um estado de código específico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Estado encontrado", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = EstadoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                                operationRef = "https://localhost:8080/estados/{estadoId}",
                                parameters = {
                                    @LinkParameter(name = "Código do estado", expression = "{estadoId}")
                                }
                        )
                }),
                @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
                @ApiResponse(responseCode = "400", description = "ID de Estado inválido", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
            }
    )
    EstadoModel buscar(@PathVariable Integer estadoId);

    @Operation(summary = "Cadastra um estado", description = "Cadastra um estado no banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estado adicionado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = EstadoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/estados/{estadoId}",
                                    parameters = {
                                            @LinkParameter(name = "Código do estado", expression = "{estadoId}")
                                    }
                            )
                    })
            }
    )
    EstadoModel adicionar(@Valid @RequestBody EstadoInput estado);

    @Operation(summary = "Atualiza um estado", description = "Atualiza um estado de código específico no banco de " +
            "dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estado adicionado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = EstadoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/estados/{estadoId}",
                                    parameters = {
                                            @LinkParameter(name = "Código do estado", expression = "{estadoId}")
                                    }
                            )
                    }),
                    @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de Estado inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
            }
    )
    EstadoModel atualizar(@PathVariable Integer estadoId, @Valid @RequestBody EstadoInput estado);

    @Operation(summary = "Remove um computador", description = "Remove um computador de código específico cadastrado " +
            "no banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de Estado inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
            }
    )
    void remover(@PathVariable Integer estadoId);
}
