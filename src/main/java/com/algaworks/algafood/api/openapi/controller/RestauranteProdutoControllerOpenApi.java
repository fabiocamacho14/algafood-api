package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestauranteProdutoControllerOpenApi {

    List<ProdutoModel> listar(@PathVariable Integer restauranteId, @RequestParam(required = false) boolean incluirInativos);

    @Operation(summary = "Busca um produto de um restaurante", description = "Busca um produto de código específico " +
            "dentro de um restaurante específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ProdutoModel buscar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId);

    @Operation(summary = "Adiciona um produto a um restaurante", description = "Adiciona um produto a um restaurante " +
            "de código específico", responses = {
            @ApiResponse(responseCode = "200", description = "Produto criado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
            )),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId);

    @Operation(summary = "Atualiza um produto de um restaurante", description = "Atualiza um produto de um " +
            "restaurante de código específico", responses = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = ProdutoModel.class)
            )),
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ProdutoModel atualizar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId, @PathVariable Integer produtoId);
}
