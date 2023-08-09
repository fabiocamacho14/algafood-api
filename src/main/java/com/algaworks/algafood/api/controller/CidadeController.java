package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Integer cidadeId) {
        return cidadeModelAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidade) {
        try {
            Cidade cidadeInserir = cidadeInputDisassembler.toDomainObject(cidade);
            return cidadeModelAssembler.toModel(cadastroCidade.adicionar(cidadeInserir));

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Integer cidadeId, @Valid @RequestBody CidadeInput cidade) {
        cadastroCidade.buscarOuFalhar(cidadeId);

        try {
            Cidade cidadeAtualizar = cidadeInputDisassembler.toDomainObject(cidade);
            return cidadeModelAssembler.toModel(cadastroCidade.adicionar(cidadeAtualizar));

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer cidadeId) {
            cadastroCidade.excluir(cidadeId);
    }
}
