package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Controlador de estatísticas", description = "Todos os controladores referentes a estatísticas de vendas")
public interface EstatisticasControllerOpenApi {

    @Operation(summary = "Consultar vendas diárias", description = "Lista todas as vendas de um dia específico",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista retornada", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = VendaDiaria.class)
                ))
            })
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter,
                                             @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet);

    @Operation(summary = "Consultar vendas diárias", description = "Lista todas as vendas de um dia específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada", content = @Content(
                            mediaType = "application/pdf"
                    ))
            })
    ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter vendaDiariaFilter,
                                                     @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet);
}
