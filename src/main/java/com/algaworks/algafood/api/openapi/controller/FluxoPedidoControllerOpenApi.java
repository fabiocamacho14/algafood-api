package com.algaworks.algafood.api.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Controlador de Fluxo de pedido")
public interface FluxoPedidoControllerOpenApi {

    @Operation(summary = "Confirma um pedido", description = "Confirma um pedido de código específico cadastrado no " +
            "banco de dados do sistema.")
    ResponseEntity<Void> confirmar(@PathVariable String codigoPedido);

    @Operation(summary = "Entrega um pedido", description = "Entrega um pedido de código específico cadastrado no " +
            "banco de dados do sistema.")
    ResponseEntity<Void> entregar(@PathVariable String codigoPedido);

    @Operation(summary = "Cancela um pedido", description = "Cancela um pedido de código específico cadastrado no " +
            "banco de dados do sistema.")
    ResponseEntity<Void> cancelar(@PathVariable String codigoPedido);
}
