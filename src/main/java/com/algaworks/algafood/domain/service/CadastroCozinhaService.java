package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    public static final String MENSAGEM_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Integer cozinhaId) {
        buscarOuFalhar(cozinhaId);

        try {
          cozinhaRepository.deleteById(cozinhaId);
            cozinhaRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MENSAGEM_COZINHA_EM_USO,
                    cozinhaId));

        }
    }

    public Cozinha buscarOuFalhar(Integer cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradoException(cozinhaId));
    }
}
