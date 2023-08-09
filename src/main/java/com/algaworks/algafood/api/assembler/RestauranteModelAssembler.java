package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

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

        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModel)
                .toList();
    }
}
