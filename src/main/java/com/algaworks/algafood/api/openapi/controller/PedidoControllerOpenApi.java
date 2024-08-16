package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controlador de pedidos", description = "Todos os controladores referentes aos pedidos cadastrados no " +
        "banco de dados do sistema")
public interface PedidoControllerOpenApi {

    @Operation(
            summary = "Lista todos os pedidos", description = "Lista todos os pedidos cadastrados no banco de " +
            "dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = PedidoResumoModel.class)
                    ), links = {
                            @Link(name = "self", description = "self link",
                                    operationRef = "https://localhost:8080/pedidos"
                            )
                    })
            }
    )
    MappingJacksonValue listar(@Parameter(name = "Campos", description = "Os campos que deseja filtrar") String campos);

    @Operation(
            summary = "Busca um pedido específico", description = "Busca um pedido de código específico cadastrado no" +
            " banco de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "ID de pedido inválido", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                    )),
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = PedidoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/pedidos/{pedidoId}",
                                    parameters = {
                                        @LinkParameter(name = "Código do pedido", expression = "{pedidoId}")
                                    }
                            ),
                            @Link(name = "confirmar", description = "Link para confirmar pedido",
                                    operationRef = "https://localhost:8080/pedidos/{pedidoId}/confirmacao",
                                    parameters = {
                                        @LinkParameter(name = "Código de pedido", expression = "{pedidoId}")
                                    }
                            ),
                            @Link(name = "collection", description = "Collection Link",
                                operationRef = "https://localhost:8080/pedidos"
                            )
                    })
            }
    )
    PedidoModel buscar(@PathVariable String codigoPedido);

    @Operation(
            summary = "Adiciona um pedido", description = "Adiciona um pedido no banco de dados do sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido adicionado", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = PedidoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/pedidos/{pedidoId}",
                                    parameters = {
                                            @LinkParameter(name = "Código do pedido", expression = "{pedidoId}")
                                    }
                            ),
                            @Link(name = "confirmar", description = "Link para confirmar pedido",
                                    operationRef = "https://localhost:8080/pedidos/{pedidoId}/confirmacao",
                                    parameters = {
                                            @LinkParameter(name = "Código de pedido", expression = "{pedidoId}")
                                    }
                            ),
                            @Link(name = "collection", description = "Collection Link",
                                    operationRef = "https://localhost:8080/pedidos"
                            )
                    })
            }
    )
    PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput);
}
