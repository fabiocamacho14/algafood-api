package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.CidadeResumoModel;
import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.UsuarioPutInput;
import com.algaworks.algafood.api.model.input.UsuarioPutSenhaInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.Usuario;
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
        modelMapper.createTypeMap(Endereco.class, EnderecoModel.class)
                .<String>addMapping(mapper -> mapper.getCidade().getEstado().getNome(),
                        (mapper, value) -> mapper.getCidade().setEstado(value));
        modelMapper.createTypeMap(Usuario.class, UsuarioPutInput.class)
                .addMapping(Usuario::getNome, UsuarioPutInput::setNome)
                .addMapping(Usuario::getEmail, UsuarioPutInput::setEmail);
//        modelMapper.createTypeMap(Usuario.class, UsuarioPutSenhaInput.class)
//                .addMapping(Usuario::getSenha, UsuarioPutSenhaInput::setSenhaNova);
        modelMapper.emptyTypeMap(UsuarioPutSenhaInput.class, Usuario.class)

                .addMapping(UsuarioPutSenhaInput::getSenhaNova, Usuario::setSenha);
//        modelMapper.getTypeMap(UsuarioPutSenhaInput.class, Usuario.class).
//                addMappings(mapper -> mapper.skip());

        return modelMapper;
    }
}
