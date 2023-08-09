package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        modelMapper.emptyTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMappings(mapper -> mapper.<CidadeModel>map(src -> src.getEndereco().getCidade(), (dest, v) -> dest.getEndereco().setCidade(v)))
////                .addMappings(mapper -> mapper.<Integer>map(src -> src.getEndereco().getCidade().getId(), (dest, v) -> dest.getEndereco().getCidade().setId(v)))
//                .addMappings(mapper -> mapper.<String>map(src -> src.getEndereco().getCidade().getNome(), (dest, v) -> dest.getEndereco().getCidade().setNome(v)))
//                .implicitMappings();
        return modelMapper;
    }
}
