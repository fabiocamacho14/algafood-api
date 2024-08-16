package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping
    public CollectionModel<GrupoModel> listarGrupos(@PathVariable Integer usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        
        CollectionModel<GrupoModel> grupoModelCollection = grupoModelAssembler.toCollectionModel(usuario.getGrupos());

        grupoModelCollection.getContent().forEach(grupoModel -> grupoModel.add(algaLinks.linkToDesassociarGrupos(usuarioId, grupoModel.getId(), "desassociar")));

        grupoModelCollection.add(algaLinks.linkToAssociarGrupos(usuarioId, "associar"));

        return grupoModelCollection;
    }

    @Override
    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desassociarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
