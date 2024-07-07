package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface CozinhaControllerOpenApi {

    @Operation(
            summary = "Lista todas as cozinhas",
            description = "Lista todas as cozinhas cadastradas no banco de dados do sistema"
    )
    Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable);

    @Operation(
            summary = "Busca uma cozinha",
            description = "Busca uma cozinha de código específico no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de cozinha inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    ))
            }
    )
    CozinhaModel buscar(@PathVariable Integer cozinhaId);

    @Operation(
            summary = "Adiciona uma cozinha",
            description = "Adiciona uma cozinha ao banco de dados do sistema"
    )
    CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput);

    @Operation(
            summary = "Atualiza uma cozinha",
            description = "Atualiza uma cozinha específica no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cozinha atualizada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = CozinhaModel.class)
                    )),
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
