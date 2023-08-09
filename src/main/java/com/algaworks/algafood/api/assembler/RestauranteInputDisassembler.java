package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
//        Restaurante restaurante = new Restaurante();
//        restaurante.setNome(restauranteInput.getNome());
//        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
//        restaurante.setEndereco(restauranteInput.getEndereco());
//
//        Cozinha cozinha = new Cozinha();
//        cozinha.setId(restauranteInput.getCozinha().getId());
//
//        restaurante.setCozinha(cozinha);
//
//        return restaurante;

        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
//        Para evitar HibernateException: identifier of an instance of bla bla bla
        restaurante.setCozinha(new Cozinha());
//        restaurante.setEndereco(new Endereco());
//        restaurante.getEndereco().setCidade(new Cidade());

        modelMapper.map(restauranteInput, restaurante);
    }
}
