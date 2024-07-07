package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements com.algaworks.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Override
    @GetMapping
    public Collection<GrupoModel> listarGrupos(@PathVariable Integer usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @Override
    @PutMapping("/{grupoId}")
    public void associarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

    @Override
    @DeleteMapping("/{grupoId}")
    public void desassociarGrupo(@PathVariable Integer usuarioId, @PathVariable Integer grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
