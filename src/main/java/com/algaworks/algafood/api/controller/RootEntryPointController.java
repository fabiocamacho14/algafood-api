package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToCozinhas().withRel("cozinhas"));
        rootEntryPointModel.add(algaLinks.linkToPedidos().withRel("pedidos"));
        rootEntryPointModel.add(algaLinks.linkToRestaurantesCollection().withRel("restaurantes"));
        rootEntryPointModel.add(algaLinks.linkToGrupos().withRel("grupos"));
        rootEntryPointModel.add(algaLinks.linkToUsuarios().withRel("usuarios"));
        rootEntryPointModel.add(algaLinks.linkToPermissao().withRel("permissoes"));
        rootEntryPointModel.add(algaLinks.linkToFormasPagamento().withRel("formasPagamento"));
        rootEntryPointModel.add(algaLinks.linkToEstados().withRel("estados"));
        rootEntryPointModel.add(algaLinks.linkToCidades().withRel("cidades"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
