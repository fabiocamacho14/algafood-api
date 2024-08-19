package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CozinhaController.class);

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @Override
    @GetMapping
    public PagedModel<CozinhaModel> listar(@PageableDefault() Pageable pageable) {
        LOGGER.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());

//        if (true) {
//            throw new RuntimeException("Teste de exception");
//        }

        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModel> cozinhaModelPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
//        i can do whatever i want with this object before return him
        cozinhaModelPagedModel.toString();

        return cozinhaModelPagedModel;
    }

    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Integer cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.adicionar(cozinha));
    }


    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Integer cozinhaId, @Valid @RequestBody CozinhaInput cozinha) {
        cadastroCozinha.buscarOuFalhar(cozinhaId);

        Cozinha cozinhaAtualizar = cozinhaInputDisassembler.toDomainObject(cozinha);
        return cozinhaModelAssembler.toModel(cadastroCozinha.adicionar(cozinhaAtualizar));
    }

    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }
}
