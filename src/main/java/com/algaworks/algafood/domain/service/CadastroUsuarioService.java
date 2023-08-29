package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario adicionar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
           throw new NegocioException("Já existe um usuário cadastrado com o email " + usuario.getEmail());
        }

        return usuarioRepository.save(usuario);
    }



    @Transactional
    public void remover(Integer usuarioId) {
        buscarOuFalhar(usuarioId);

        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Usuário de id %d não pode ser removido, pois está em uso", usuarioId));
        }
    }

    public Usuario buscarOuFalhar(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
