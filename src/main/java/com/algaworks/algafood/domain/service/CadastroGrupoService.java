package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissao;

    @Transactional
    public Grupo adicionar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void remover(Integer grupoId) {
        buscarOuFalhar(grupoId);
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Grupo de id %d não pode ser removido, pois está em uso", grupoId));
        }
    }

    @Transactional
    public void associarPermissao(Integer grupoId, Integer permissaoId) {
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
        Grupo grupo = buscarOuFalhar(grupoId);

        if (!grupo.adicionarPermissao(permissao)) {
            throw new NegocioException(String.format("Permissão de código %d já associada com grupo de código %d",
                    permissao.getId(), grupo.getId()));
        }
    }

    @Transactional
    public void desassociarPermissao(Integer grupoId, Integer permissaoId) {
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
        Grupo grupo = buscarOuFalhar(grupoId);

        if (!grupo.adicionarPermissao(permissao)) {
            throw new NegocioException(String.format("Permissão de código %d não está associada com grupo de código %d",
                    permissao.getId(), grupo.getId()));
        }
    }

    public Grupo buscarOuFalhar(Integer grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
