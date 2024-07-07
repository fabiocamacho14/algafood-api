package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Tag(name = "Controlador de grupos de usuários", description = "Todos os controladores referentes a associação de " +
        "grupos com usuários")
public interface UsuarioGrupoControllerOpenApi {


    @Operation(summary = "Listar grupos de usuário", description = "Lista todos os grupos que o usuário de código " +
            "específico pertence")
    Collection<GrupoModel> listarGrupos(@PathVariable Integer usuarioId);

    @Operation(summary = "Associa um grupo a um usuário", description = "Associa um usuário de código específico a um" +
            " grupo de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Entidade inválida", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void associarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId);

    @Operation(summary = "Desassocia um grupo a um usuário", description = "Desassocia um usuário de código " +
            "específico a um grupo de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Entidade inválida", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void desassociarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId);
}
