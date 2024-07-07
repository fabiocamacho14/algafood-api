package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controlador de cidades", description = "Todos os controladores referentes a cidades cadastradas")
public interface CidadeControllerOpenApi {

    List<CidadeModel> listar();

    @Operation(
            summary = "Busca cidade de código específico",
            description = "Busca alguma cidade que tenha um código de ID específico no banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Id da cidade inválido", content =
                    @Content(mediaType =
                            "application/json", schema = @Schema(implementation = Problem.class))),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content =
                    @Content(mediaType =
                            "application/json", schema = @Schema(implementation = Problem.class)))
            }
    )
    CidadeModel buscar(@PathVariable Integer cidadeId);

    @Operation(
            summary = "Adiciona uma cidade",
            description = "Adiciona uma cidade pertecente a um estado no banco de dados do sistema."
    )
    CidadeModel adicionar(@RequestBody @Valid CidadeInput cidade);

    @Operation(
            summary = "Atualiza uma cidade",
            description = "Atualiza uma cidade pertecente a um estado no banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cidade atualizada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = CidadeModel.class)
                    ))
            }
    )
    CidadeModel atualizar(@PathVariable Integer cidadeId, @Valid @RequestBody CidadeInput cidade);

    @Operation(
            summary = "Exclui uma cidade",
            description = "Exclui uma cidade do banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cidade excluída"),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content =
                    @Content(mediaType =
                            "application/json", schema = @Schema(implementation = Problem.class)))
            }
    )
    void excluir(@PathVariable Integer cidadeId);
}
