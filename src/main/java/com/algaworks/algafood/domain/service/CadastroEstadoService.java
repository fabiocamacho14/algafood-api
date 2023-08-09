package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado adicionar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Integer estadoId) {
        buscarOuFalhar(estadoId);

        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO,
                    estadoId));

        }
    }

    public Estado buscarOuFalhar(Integer estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }
}
