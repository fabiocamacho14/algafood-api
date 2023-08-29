package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Integer grupoId) {
        return grupoModelAssembler.toModel(cadastroGrupo.buscarOuFalhar(grupoId));
    }

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoInsercao = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupo.adicionar(grupoInsercao));
    }

    @PutMapping("/{grupoId}")

    public GrupoModel atualizar(@PathVariable Integer grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtualizar =  cadastroGrupo.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtualizar);
        return grupoModelAssembler.toModel(cadastroGrupo.adicionar(grupoAtualizar));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer grupoId) {
        cadastroGrupo.remover(grupoId);
    }
}
