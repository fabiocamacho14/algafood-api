package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.openapi.controller.FormaPagamentoOpenApi;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/formaPagamentos")
@CrossOrigin
public class FormaPagamentoController implements FormaPagamentoOpenApi {

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @Override
    @GetMapping()
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

//      já temos condições de saber se continua ou não o processamento
        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamentoModel> formasPagamento =
                formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)))
//                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)).cachePrivate())
//                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)).cachePublic())
//                .cacheControl(CacheControl.noCache())
//                .cacheControl(CacheControl.noStore())
                .eTag(eTag)
                .body(formasPagamento);
    }

    @Override
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable @Valid Integer formaPagamentoId,
                                                      ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamentoModel formaPagamentoModel =
                formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)))
                .body(formaPagamentoModel);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
            FormaPagamento formaPagamentoInserir = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
            return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.adicionar(formaPagamentoInserir));
    }

    @Override
    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Integer formaPagamentoId, @Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento =  cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamento);
        return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.adicionar(formaPagamento));
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);
    }
}
