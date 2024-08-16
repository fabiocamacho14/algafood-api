package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioPutInput;
import com.algaworks.algafood.api.model.input.UsuarioPutSenhaInput;
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

@Tag(name = "Controlador de usuários", description = "Todos os controladores referentes a usuários cadastrados")
public interface UsuarioControllerOpenApi {

    @Operation(summary = "Busca um usuário", description = "Busca um usuário no banco de dados do sistema",
            responses = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Usuário de ID inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)
            ), links = {
                    @Link(name = "Self", description = "Link Self",
                            operationRef = "https://localhost:8080/usuarios/{usuarioId}",
                    parameters = {
                        @LinkParameter(name = "ID do usuário", expression = "{usuarioId]")
                    }),
                    @Link(name = "collection", description = "Collection link",
                            operationRef = "https://localhost:8080/usuarios"
                    ),
                    @Link(name = "grupos-usuarios", description = "Grupos do usuário")
            })
    })
    UsuarioModel buscar(@PathVariable Integer usuarioId);

    @Operation(summary = "Lista todos os usuários", description = "Lista todos os usuários cadastrados no banco de " +
            "dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Lista de usuários", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)
                    ), links = {
                            @Link(name = "self", description = "Link Self",
                                    operationRef = "https://localhost:8080/usuarios"
                            )
                    })
    })
    CollectionModel<UsuarioModel> listar();

    @Operation(summary = "Adiciona um usuário", description = "Adiciona um usuário no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)
                    ), links = {
                                    @Link(name = "Self", description = "Link Self",
                                            operationRef = "https://localhost:8080/usuarios/{usuarioId}",
                                            parameters = {
                                                    @LinkParameter(name = "ID do usuário", expression = "{usuarioId]")
                                            }),
                                    @Link(name = "collection", description = "Collection link",
                                            operationRef = "https://localhost:8080/usuarios"
                                    ),
                                    @Link(name = "grupos-usuarios", description = "Grupos do usuário")
                    })
            })
    UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput);

    @Operation(summary = "Exclui um usuário", description = "Exclui um usuário cadastrado no banco de dados do sistema",
            responses = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Usuário de ID inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "204", description = "Usuário removido")
    })
    void excluir(@PathVariable Integer usuarioId);

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário cadastrado no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Usuário de ID inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)
                    ), links = {
                            @Link(name = "Self", description = "Link Self",
                                    operationRef = "https://localhost:8080/usuarios/{usuarioId}",
                                    parameters = {
                                            @LinkParameter(name = "ID do usuário", expression = "{usuarioId]")
                                    }),
                            @Link(name = "collection", description = "Collection link",
                                    operationRef = "https://localhost:8080/usuarios"
                            ),
                            @Link(name = "grupos-usuarios", description = "Grupos do usuário")
                    })
            })
    UsuarioModel atualizar(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioPutInput usuarioPutInput);

    @Operation(summary = "Atualiza a senha de um usuário", description = "Atualiza a senha de um usuário cadastrado " +
            "no banco de dados do sistema", responses = {
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Usuário de ID inválido/Mesma senha", content =
                    @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "204", description = "Senha atualizada")
            })
    void atualizarSenha(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioPutSenhaInput usuarioPutSenhaInput);
}
