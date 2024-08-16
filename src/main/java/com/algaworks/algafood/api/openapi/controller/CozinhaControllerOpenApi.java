package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de cozinhas", description = "Todos os controladores relativos a cozinhas cadastradas")
public interface CozinhaControllerOpenApi {

    @Operation(
            summary = "Lista todas as cozinhas",
            description = "Lista todas as cozinhas cadastradas no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = PagedModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/cozinhas"
                            )
                    })
            }
    )
    PagedModel<CozinhaModel> listar(Pageable pageable);

    @Operation(
            summary = "Busca uma cozinha",
            description = "Busca uma cozinha de código específico no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de cozinha inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "200", description = "Cozinha encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = CozinhaModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/cozinhas/{cozinhaId}",
                                    parameters = {
                                        @LinkParameter(name = "Código de cozinha", expression = "{cozinhaId}")
                                    }
                            ),
                            @Link(name = "collection", description = "collection link",
                                    operationRef = "https://localhost:8080/cozinhas"
                            )
                    })
            }
    )
    CozinhaModel buscar(@PathVariable Integer cozinhaId);

    @Operation(
            summary = "Adiciona uma cozinha",
            description = "Adiciona uma cozinha ao banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cozinha adicionada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = CozinhaModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/cozinhas/{cozinhaId}",
                                    parameters = {
                                            @LinkParameter(name = "Código de cozinha", expression = "{cozinhaId}")
                                    }
                            ),
                            @Link(name = "collection", description = "collection link",
                                    operationRef = "https://localhost:8080/cozinhas"
                            )
                    })
            }
    )
    CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput);

    @Operation(
            summary = "Atualiza uma cozinha",
            description = "Atualiza uma cozinha específica no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cozinha atualizada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = CozinhaModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/cozinhas/{cozinhaId}",
                                    parameters = {
                                            @LinkParameter(name = "Código de cozinha", expression = "{cozinhaId}")
                                    }
                            ),
                            @Link(name = "collection", description = "collection link",
                                    operationRef = "https://localhost:8080/cozinhas"
                            )
                    }),
                    @ApiResponse(responseCode = "404", description = "ID de cozinha inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    CozinhaModel atualizar(@PathVariable Integer cozinhaId, @Valid @RequestBody CozinhaInput cozinha);

    @Operation(
            summary = "Exclui uma cozinha",
            description = "Exclui uma cozinha de código específico do banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de cozinha inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    void remover(@PathVariable Integer cozinhaId);
}
