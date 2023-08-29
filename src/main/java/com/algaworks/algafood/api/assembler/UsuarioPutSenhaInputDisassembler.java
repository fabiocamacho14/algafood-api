package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.UsuarioPutSenhaInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPutSenhaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioPutSenhaInput usuarioPutSenhaInput) {
        return modelMapper.map(usuarioPutSenhaInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioPutSenhaInput usuarioPutSenhaInput, Usuario usuario) {
        modelMapper.map(usuarioPutSenhaInput, usuario);
    }
}
