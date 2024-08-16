package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.model.PermissaoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name= "Controlador de Permissões de Grupo", description = "Controlador responsável pelo gerenciamento de " +
        "permissões de grupos.")
public interface GrupoPermissoesControllerOpenApi {

    @Operation(summary = "Lista todas as permissões", description = "Lista todas as permissões que o grupo possui",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = PermissaoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/grupos/{grupoId}/permissoes",
                            parameters = {
                                @LinkParameter(name = "Código de grupo", expression = "{grupoId}")
                            }
                        ),
                        @Link(name = "associar", description = "Link de associação de permissão",
                                operationRef = "https://localhost:8080/grupos/1/permissoes/{permissaoId}",
                                parameters = {
                                        @LinkParameter(name = "Código de grupo", expression = "{grupoId}"),
                                        @LinkParameter(name = "Código de permissão", expression = "{permissaoId}")

                                }
                        )
                })
            }
    )
    CollectionModel<PermissaoModel> listar(@PathVariable Integer grupoId);

    @Operation(summary = "Associa uma permissão", description = "Associa uma permissão de código específico a um " +
            "grupo de código específico cadastrado no banco de dados do sistema.")
    ResponseEntity<Void> associarPermissao(@PathVariable Integer grupoId, @PathVariable Integer permissaoId);

    @Operation(summary = "Desassocia uma permissão", description = "Desassocia uma permissão de códgio específico a " +
            "um grupo de código específico cadastrado no banco de dados do sistema.")
    ResponseEntity<Void> desassociarPermissao(@PathVariable Integer grupoId, @PathVariable Integer permissaoId);
}
