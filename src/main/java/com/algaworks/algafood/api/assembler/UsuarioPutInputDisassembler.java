package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.UsuarioPutInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPutInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioPutInput usuarioPutInput) {
        return modelMapper.map(usuarioPutInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioPutInput usuarioPutInput, Usuario usuario) {
        modelMapper.map(usuarioPutInput, usuario);
    }
}
