package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;


    @Transactional
    public Cidade adicionar(Cidade cidade) {
        Integer estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(Integer cidadeId) {
        buscarOuFalhar(cidadeId);

        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO,
                    cidadeId));
        }

    }

    public Cidade buscarOuFalhar(Integer cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradoException(cidadeId));
    }
}
