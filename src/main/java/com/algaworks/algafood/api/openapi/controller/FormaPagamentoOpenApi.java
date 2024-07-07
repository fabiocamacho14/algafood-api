package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Tag(name = "Controlador de Formas de Pagamento", description = "Todos os controladores relativos a formas de " +
        "pagamento cadatradas")
public interface FormaPagamentoOpenApi {

    @Operation(
            summary = "Listar forma de pagamentos", description = "Lista todas as formas de pagamentos possíveis de " +
            "nossos restaurantes"
    )
    ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

    @Operation(summary = "Busca uma forma de pagamento", description = "Busca uma forma de pagamento de código " +
            "específico cadastrada no sistema.", responses = {
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de Forma de Pagamento inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    ResponseEntity<FormaPagamentoModel> buscar(@PathVariable @Valid Integer formaPagamentoId,
                                               ServletWebRequest request);

    @Operation(summary = "Cadastra uma Forma de Pagamento", description = "Cadastra uma Forma de Pagamento no banco " +
            "de dados do sistema."
    )
    FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput);

    @Operation(summary = "Atualiza uma Forma de Pagamento", description = "Atualiza uma Forma de Pagamento de ID " +
            "específico cadastrada no banco de dados.", responses = {
            @ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de Forma de Pagamento inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
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
