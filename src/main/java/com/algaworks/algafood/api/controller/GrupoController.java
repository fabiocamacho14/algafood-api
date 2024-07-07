package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Integer grupoId) {
        return grupoModelAssembler.toModel(cadastroGrupo.buscarOuFalhar(grupoId));
    }

    @Override
    @GetMapping
    public Set<GrupoModel> listar() {
        return (Set<GrupoModel>) grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoInsercao = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupo.adicionar(grupoInsercao));
    }

    @Override
    @PutMapping("/{grupoId}")

    public GrupoModel atualizar(@PathVariable Integer grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtualizar =  cadastroGrupo.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtualizar);
        return grupoModelAssembler.toModel(cadastroGrupo.adicionar(grupoAtualizar));
    }

    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer grupoId) {
        cadastroGrupo.remover(grupoId);
    }
}
