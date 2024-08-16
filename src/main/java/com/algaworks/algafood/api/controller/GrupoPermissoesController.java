package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.openapi.controller.GrupoPermissoesControllerOpenApi;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController implements GrupoPermissoesControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Integer grupoId) {
        CollectionModel<PermissaoModel> permissaoModelCollection =
                permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());

        permissaoModelCollection.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algaLinks.linkToDesassociarPermissao(grupoId, permissaoModel.getId(), "desassociar"));
        });

        permissaoModelCollection.add(algaLinks.linkToAssociarPermissao(grupoId, "associar"));

        return permissaoModelCollection;
    }

    @Override
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarPermissao(@PathVariable Integer grupoId, @PathVariable Integer permissaoId) {
        cadastroGrupo.associarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociarPermissao(@PathVariable Integer grupoId, @PathVariable Integer permissaoId) {
        cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
}
