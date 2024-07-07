package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestauranteFormaPagamentoControllerOpenApi {

    List<FormaPagamentoModel> listar(@PathVariable Integer restauranteId);

    @Operation(summary = "Desassocia uma forma de pagamento", description = "Desassocia uma forma de pagamento a um " +
            "restaurante", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Forma de pagamento não associada com restaurante",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void desassociarFormaPagamento(@PathVariable Integer restauranteId, @PathVariable Integer formaPagamentoId);

    @Operation(summary = "Associa uma forma de pagamento", description = "Associa uma forma de pagamento a um " +
            "restaurante", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Forma de pagamento já associada com restaurante",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))

    })
    void associarFormaPagamento(@PathVariable Integer restauranteId, @PathVariable Integer formaPagamentoId);
}
