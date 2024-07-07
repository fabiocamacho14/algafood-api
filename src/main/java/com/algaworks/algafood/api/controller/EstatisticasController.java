package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.openapi.controller.EstatisticasControllerOpenApi;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estatisticas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Controlador de estatísticas", description = "Todos os controles relativos a estatísticas de vendas " +
        "registradas no banco de dados do sistema.")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @Override
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        return vendaQueryService.consultarVendasDiarias(vendaDiariaFilter, timeOffSet);
    }

    @Override
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter vendaDiariaFilter,
                                                            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
//        return vendaQueryService.consultarVendasDiarias(vendaDiariaFilter, timeOffSet);

        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(vendaDiariaFilter, timeOffSet);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
    }
}
