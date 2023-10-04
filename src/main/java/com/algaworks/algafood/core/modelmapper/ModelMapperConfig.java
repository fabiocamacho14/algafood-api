package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.*;
import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.model.input.UsuarioPutInput;
import com.algaworks.algafood.api.model.input.UsuarioPutSenhaInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


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
//        modelMapper.createTypeMap(Pedido.class, PedidoResumoModel.class)
//                .addMappings(mapper -> mapper.<String>map(src -> src.getUsuario().getNome(), (dest, v) -> dest.getCliente().setNome(v)))
//                .addMappings(mapper -> mapper.<String>map(src -> src.getUsuario().getEmail(), (dest, v) -> dest.getCliente().setEmail(v)));
        modelMapper.createTypeMap(Pedido.class, PedidoResumoModel.class)
                .addMapping(Pedido::getUsuario, PedidoResumoModel::setCliente);

//        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
//                .addMapping(ItemPedidoInput::getProdutoId, ItemPedido::setId)
//                .addMapping(ItemPedidoInput::getObservacao, ItemPedido::setObservacao)
//                .addMapping(ItemPedidoInput::getQuantidade, ItemPedido::setQuantidade);


        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));
//                .addMapping(ItemPedidoInput::getProdutoId, ItemPedido::setProduto);

        modelMapper.createTypeMap(Pedido.class, PedidoModel.class)
                .addMapping(src -> src.getRestaurante().getTaxaFrete(), (dest, value) -> dest.setTaxaFrete((BigDecimal) value))
//                .addMapping(Pedido::getUsuario, (dest, value) -> dest.setTaxaFrete((BigDecimal) value));
                .addMapping(Pedido::getUsuario, PedidoModel::setCliente);
        return modelMapper;
    }
}
