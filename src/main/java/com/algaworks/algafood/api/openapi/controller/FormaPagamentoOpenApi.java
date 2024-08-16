package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

@Tag(name = "Controlador de Formas de Pagamento", description = "Todos os controladores relativos a formas de " +
        "pagamento cadatradas")
public interface FormaPagamentoOpenApi {

    @Operation(
            summary = "Listar forma de pagamentos", description = "Lista todas as formas de pagamentos possíveis de " +
            "nossos restaurantes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = FormaPagamentoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                operationRef = "https://localhost:8080/formaPagamentos"
                            )
                    })
            }
    )
    ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

    @Operation(summary = "Busca uma forma de pagamento", description = "Busca uma forma de pagamento de código " +
            "específico cadastrada no sistema.", responses = {
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de Forma de Pagamento inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = FormaPagamentoModel.class)
            ), links = {
                    @Link(name = "self", description = "Self Link",
                        operationRef = "https://localhost:8080/formaPagamentos/{formaPagamentoId}",
                        parameters = {
                            @LinkParameter(name = "ID de forma de pagamento", expression = "{formaPagamentoId}")
                        }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                        operationRef = "https://locallhost:8080/formaPagamentos"
                    )
            })
    })
    ResponseEntity<FormaPagamentoModel> buscar(@PathVariable @Valid Integer formaPagamentoId,
                                               ServletWebRequest request);

    @Operation(summary = "Cadastra uma Forma de Pagamento", description = "Cadastra uma Forma de Pagamento no banco " +
            "de dados do sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Forma de Pagamento criada", content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = FormaPagamentoModel.class)
                    ), links = {
                            @Link(name = "self", description = "Self Link",
                                    operationRef = "https://localhost:8080/formaPagamentos/{formaPagamentoId}",
                                    parameters = {
                                            @LinkParameter(name = "ID de forma de pagamento", expression = "{formaPagamentoId}")
                                    }
                            ),
                            @Link(name = "collection", description = "Collection Link",
                                    operationRef = "https://locallhost:8080/formaPagamentos"
                            )
                    })
            }
    )
    FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput);

    @Operation(summary = "Atualiza uma Forma de Pagamento", description = "Atualiza uma Forma de Pagamento de ID " +
            "específico cadastrada no banco de dados.", responses = {
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de Forma de Pagamento inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "201", description = "Forma de Pagamento atualizada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = FormaPagamentoModel.class)
            ), links = {
                    @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/formaPagamentos/{formaPagamentoId}",
                            parameters = {
                                    @LinkParameter(name = "ID de forma de pagamento", expression = "{formaPagamentoId}")
                            }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                            operationRef = "https://locallhost:8080/formaPagamentos"
                    )
            })
    })
    FormaPagamentoModel atualizar(@PathVariable Integer formaPagamentoId, @Valid @RequestBody FormaPagamentoInput formaPagamentoInput);

    @Operation(summary = "Exclui uma Forma de Pagamento", description = "Exclui uma Forma de Pagamento de ID " +
            "específico cadastrada no banco de dados.", responses = {
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de Forma de Pagamento inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void excluir(@PathVariable Integer formaPagamentoId);
}
