package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.openapi.controller.RestauranteResponsavelControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelController implements RestauranteResponsavelControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Override
    @GetMapping
    public Collection<UsuarioModel> listar(@PathVariable Integer restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionList(restaurante.getResponsaveis());
    }

    @Override
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId) {
        cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
    }

    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarResposavel(@PathVariable Integer restauranteId, @PathVariable Integer usuarioId) {
        cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
    }
}
