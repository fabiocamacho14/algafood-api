package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Tag(name = "Controlador de grupos", description = "Todos os controladores referentea a grupos cadastrados")
public interface GrupoControllerOpenApi {

    @Operation(
            summary = "Busca um grupo", description = "Busca um grupo de código específico dentro do banco de dados " +
            "do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de grupo inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    GrupoModel buscar(@PathVariable @Parameter(description = "Id do grupo") Integer grupoId);

    @Operation(
            summary = "Busca todos os grupos", description = "Busca todos os grupos cadastrados do banco de dados do " +
            "sistema"
    )
    Set<GrupoModel> listar();

    @Operation(
            summary = "Adiciona um grupo", description = "Adiciona um grupo dentro do banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Grupo criado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = GrupoModel.class)
                    ))
            }
    )
    GrupoModel adicionar(@RequestBody @Parameter(name = "grupo", description = "Representação de um grupo") @Valid GrupoInput grupoInput);

    @Operation(
            summary = "Atualiza um grupo", description = "Atualiza um grupo do banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Grupo atualizado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = GrupoModel.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    GrupoModel atualizar(@PathVariable @Parameter(description = "Id do grupo") Integer grupoId, @RequestBody @Valid GrupoInput grupoInput);

    @Operation(
            summary = "Exclui um grupo", description = "Exclui um grupo do banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    void excluir(@PathVariable @Parameter(description = "Id do grupo") Integer grupoId);
}
