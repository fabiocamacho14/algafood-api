package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Controlador de grupos de usuários", description = "Todos os controladores referentes a associação de " +
        "grupos com usuários")
public interface UsuarioGrupoControllerOpenApi {


    @Operation(summary = "Listar grupos de usuário",
            description = "Lista todos os grupos que o usuário de código específico pertence",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = GrupoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/usuarios/{usuarioId}/grupos",
                            parameters = {
                                @LinkParameter(name = "Código de usuário", expression = "{usuarioId}")
                            }
                        ),
                        @Link(name = "associar", description = "Link de associação de grupo",
                            operationRef = "https://localhost:8080/usuarios/{usuarioId}/grupos/{grupoId}",
                            parameters = {
                                @LinkParameter(name = "Código de usuário", expression = "{usuarioId}"),
                                @LinkParameter(name = "Código de grupo", expression = "{grupoId}")
                            }
                        )
                })
            }
    )
    CollectionModel<GrupoModel> listarGrupos(@PathVariable Integer usuarioId);

    @Operation(summary = "Associa um grupo a um usuário", description = "Associa um usuário de código específico a um" +
            " grupo de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Entidade inválida", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ResponseEntity<Void> associarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId);

    @Operation(summary = "Desassocia um grupo a um usuário", description = "Desassocia um usuário de código " +
            "específico a um grupo de código específico", responses = {
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Entidade inválida", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ResponseEntity<Void> desassociarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId);
}
