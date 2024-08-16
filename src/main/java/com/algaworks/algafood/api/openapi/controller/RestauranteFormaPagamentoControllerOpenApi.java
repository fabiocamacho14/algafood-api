package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
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

@Tag(name = "Controlador de Forma de Pagamentos de restaurantes", description = "Controlador responsável pelo " +
        "gerenciamento das formas de pagamento aceitas por um restaurante específico")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @Operation(summary = "Lista todas as formas de pagamento", description = "Lista todas as formas de pagamento " +
            "aceitas por um restaurante específico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = FormaPagamentoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                                parameters = {
                                    @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "associar", description = "Link para associar uma forma de pagamento",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/formaPagamentos" +
                                        "/{formaPagamentoId}",
                                parameters = {
                                        @LinkParameter(name = "Código de restaurante", expression = "{restauranteId}"),
                                        @LinkParameter(name = "Código da forma de pagamento", expression =
                                                "{formaPagamentoId}")
                                }
                        )
                })
            }
    )
    CollectionModel<FormaPagamentoModel> listar(@PathVariable Integer restauranteId);

    @Operation(summary = "Desassocia uma forma de pagamento", description = "Desassocia uma forma de pagamento a um " +
            "restaurante", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Forma de pagamento não associada com restaurante",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Integer restauranteId, @PathVariable Integer formaPagamentoId);

    @Operation(summary = "Associa uma forma de pagamento", description = "Associa uma forma de pagamento a um " +
            "restaurante", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Forma de pagamento já associada com restaurante",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))

    })
    ResponseEntity<Void> associarFormaPagamento(@PathVariable Integer restauranteId, @PathVariable Integer formaPagamentoId);
}
