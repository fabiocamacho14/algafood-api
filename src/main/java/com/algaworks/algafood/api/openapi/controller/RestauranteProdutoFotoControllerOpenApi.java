package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public interface RestauranteProdutoFotoControllerOpenApi {

    @Operation(summary = "Atualiza uma foto de um produto de um restaurante", description = "Atualiza uma foto de um " +
            "prouduto de código específico de um restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Id de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    FotoProdutoModel atualizar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId,
                               @Valid FotoProdutoInput fotoProdutoInput) throws IOException;


    @Operation(summary = "Busca uma foto", description = "Busca uma foto de um produto de código específico de um " +
            "restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada"
            ),
            @ApiResponse(responseCode = "400", description = "Id de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "200", description = "")
    })
    FotoProdutoModel buscar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId);

    @Operation(summary = "Busca uma foto", description = "Busca uma foto de um produto de código específico de um " +
            "restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada"
            ),
            @ApiResponse(responseCode = "400", description = "Id de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ResponseEntity<InputStreamResource> servirFoto(@PathVariable Integer restauranteId,
                                                   @PathVariable Integer produtoId,
                                                   @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @Operation(summary = "Exclui uma foto", description = "Exclui uma foto de um produto de código específico de um " +
            "restaurante de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada"
            ),
            @ApiResponse(responseCode = "400", description = "Id de entidade inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void excluirFoto(@PathVariable Integer restauranteId,
                     @PathVariable Integer produtoId);
}
