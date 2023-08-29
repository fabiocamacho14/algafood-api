package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroFormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento adicionar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Integer formaPagamentoId) {
        buscarOuFalhar(formaPagamentoId);

        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso",
                    formaPagamentoId));
        }
    }

    public FormaPagamento buscarOuFalhar(Integer formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
