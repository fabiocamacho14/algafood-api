package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestauranteControllerOpenApi {

    @Operation(summary = "Lista todos os restaurantes", description = "Lista todos os restaurantes cadastrados no " +
            "banco de dados do sistema")
    List<RestauranteModel> listar();

    @Operation(summary = "Busca um restaurante", description = "Busca um restaurante de código específico cadastrado " +
            "no banco de dados do sistema", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    RestauranteModel buscar(@PathVariable Integer restauranteId);

    @Operation(summary = "Adiciona um restaurante", description = "Adiciona um restaurante no banco de dados do " +
            "sistema")
    RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput);

    @Operation(summary = "Atualiza um restaurante", description = "Atualiza um restaurante de código específico " +
            "cadastrado " +
            "no banco de dados do sistema", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    RestauranteModel atualizar(@PathVariable Integer restauranteId, @Valid @RequestBody RestauranteInput restauranteInput);

    @Operation(summary = "Remove um restaurante", description = "Remove um restaurante de código específico " +
            "cadastrado no banco de dados do sistema", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void remover(@PathVariable Integer restauranteId);

    @Operation(summary = "Ativa um restaurante", description = "Atualiza o estado de um restaurante inativado para " +
            "ativado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void ativar(@PathVariable Integer restauranteId);

    @Operation(summary = "Desativa um restaurante", description = "Atualiza o estado de um restaurante inativado para" +
            "desativado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void inativar(@PathVariable Integer restauranteId);

    @Operation(summary = "Abre um restaurante", description = "Atualiza o estado de um restaurante fechado para" +
            "aberto", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Restaurante já encontra-se aberto", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    void abrir(@PathVariable Integer restauranteId);

    @Operation(summary = "Fecha um restaurante", description = "Atualiza o estado de um restaurante aberto para" +
            "fechado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Restaurante já encontra-se fechado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    void fechar(@PathVariable Integer restauranteId);

    @Operation(summary = "Ativa vários restaurantes", description = "Atualiza o estado de vários restaurantes " +
            "inativados para ativados", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void ativarMultiplos(@RequestBody List<Integer> restaurantesIds);

    @Operation(summary = "Inativa vários restaurantes", description = "Atualiza o estado de um restaurante inativado " +
            "para" +
            " " +
            "ativado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void inativarMultiplos(@RequestBody List<Integer> restaurantesIds);
}
