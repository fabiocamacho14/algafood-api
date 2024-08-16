package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.CidadeResumoModel;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    public RestauranteModel toModel(Restaurante restaurante) {
//        Cozinha cozinha = restaurante.getCozinha();
//        CozinhaModel cozinhaModel = new CozinhaModel();
//        cozinhaModel.setId(cozinha.getId());
//        cozinhaModel.setNome(cozinha.getNome());
//
//        RestauranteModel restauranteModel = new RestauranteModel();
//        restauranteModel.setId(restaurante.getId());
//        restauranteModel.setNome(restaurante.getNome());
//        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
//        restauranteModel.setCozinha(cozinhaModel);
//        restauranteModel.setEndereco(restaurante.getEndereco());
//
//        return restauranteModel;

        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        CozinhaModel cozinhaModel = restauranteModel.getCozinha();
        cozinhaModel.add(algaLinks.linkToCozinhas(cozinhaModel.getId()));

        CidadeResumoModel cidadeModel = restauranteModel.getEndereco().getCidade();
        cidadeModel.add(algaLinks.linkToCidades(cidadeModel.getId()));

        restauranteModel.add(algaLinks.linkToRestaurantesCollection());
        restauranteModel.add(algaLinks.linkToInativarRestaurantes(restauranteModel.getId(), "inativar"));
        restauranteModel.add(algaLinks.linkToRestaurantesResponsaveis(restauranteModel.getId(), "responsaveis"));
        restauranteModel.add(algaLinks.linkToFormasPagamentoRestaurantes(restauranteModel.getId(), "formas-pagamento"));

        if (restaurante.getAberto()) {
            restauranteModel.add(algaLinks.linkToFecharRestaurantes(restauranteModel.getId(), "fechar"));
        } else {
            restauranteModel.add(algaLinks.linkToAbrirRestaurantes(restauranteModel.getId(), "abrir"));
        }

        return restauranteModel;
    }

//    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
//        return restaurantes.stream()
//                .map(this::toModel)
//                .toList();
//    }


    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantesCollection()
                        .withSelfRel());
    }
}
