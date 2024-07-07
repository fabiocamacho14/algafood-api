package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
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

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

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

    @Transactional
    public void associarGrupo(Integer usuarioId, Integer grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        if (!usuario.associarGrupo(grupo)) {
            throw new NegocioException("Este grupo já está associado com este usuário");
        }
    }

    @Transactional
    public void desassociarGrupo(Integer usuarioId, Integer grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        if (!usuario.desassociarGrupo(grupo)) {
            throw new NegocioException("Este grupo não está associado com este usuário");
        }
    }

    public Usuario buscarOuFalhar(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
