package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
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

@Tag(name = "Controlador de produtos de restaurantes", description = "Controlador responsável pelos produtos que um " +
        "restaurante oferece")
public interface RestauranteProdutoControllerOpenApi {

    @Operation(summary = "Lista os produtos de um restaurante", description = "Lista todos os produtos que um " +
            "restaurante pode oferecer",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/restarauntes/{restauranteId}/produtos",
                            parameters = {
                                @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}")
                            }
                        )
                })
            }
    )
    CollectionModel<ProdutoModel> listar(@PathVariable Integer restauranteId,
                                         @RequestParam(required = false) Boolean incluirInativos);

    @Operation(summary = "Busca um produto de um restaurante", description = "Busca um produto de código específico " +
            "dentro de um restaurante específico",
            responses = {
                @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
                @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
                @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
                ), links = {
                    @Link(name = "self", description = "Self Link",
                        operationRef = "https://localhost:8080/restarauntes/{restauranteId}/produtos/{produtoId}",
                        parameters = {
                                @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                        }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                        operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos",
                        parameters = {
                            @LinkParameter(name = "Código do restaurante", expression = "{restauranteId}")
                        }
                    ),
                    @Link(name = "foto", description = "Link para foto do produto",
                        operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos/{produtoId}/foto",
                        parameters = {
                                @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                        }
                    )
                })
    })
    ProdutoModel buscar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId);

    @Operation(summary = "Adiciona um produto a um restaurante", description = "Adiciona um produto a um restaurante " +
            "de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "201", description = "Produto adicionado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
            ), links = {
                    @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/restarauntes/{restauranteId}/produtos/{produtoId}",
                            parameters = {
                                    @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                    @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                            }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos",
                            parameters = {
                                    @LinkParameter(name = "Código do restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "foto", description = "Link para foto do produto",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos/{produtoId}/foto",
                            parameters = {
                                    @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                    @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                            }
                    )
            })
    })
    ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId);

    @Operation(summary = "Atualiza um produto de um restaurante", description = "Atualiza um produto de um " +
            "restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "200", description = "Produto atualizado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
            ), links = {
                    @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/restarauntes/{restauranteId}/produtos/{produtoId}",
                            parameters = {
                                    @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                    @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                            }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos",
                            parameters = {
                                    @LinkParameter(name = "Código do restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "foto", description = "Link para foto do produto",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/produtos/{produtoId}/foto",
                            parameters = {
                                    @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                    @LinkParameter(name = "Código do produto", expression = "{produtoId}")
                            }
                    )
            })
    })
    ProdutoModel atualizar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId, @PathVariable Integer produtoId);
}
