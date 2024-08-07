package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        Integer cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha =  cadastroCozinha.buscarOuFalhar(cozinhaId);
        Integer cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void excluir(Integer restauranteId) {
        buscarOuFalhar(restauranteId);

        try {
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO,
                    restauranteId));
        }
    }

    @Transactional
    public void ativar(Integer restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

//        restauranteAtual.setAtivo(Boolean.TRUE);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Integer restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

//        restauranteAtual.setAtivo(Boolean.FALSE);
        restauranteAtual.inativar();
    }

    @Transactional
    public void abrir(Integer restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        if (restaurante.getAberto() == Boolean.TRUE) {
            throw new NegocioException(String.format("Restaurante de código %d já está aberto.", restauranteId));
        }

        restaurante.abrir();
    }

    @Transactional
    public void fechar(Integer restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        if (restaurante.getAberto() == Boolean.FALSE) {
            throw new NegocioException(String.format("Restaurante de código %d já está fechado.", restauranteId));
        }

        restaurante.fechar();
    }

    @Transactional
    public void desassociarFormaPagamento(Integer restauranteId, Integer formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

//        List<FormaPagamento> formaPagamentos = restaurante.getFormasPagamento();
//        formaPagamentos.remove(formaPagamento);
//        restaurante.setFormasPagamento(formaPagamentos);

        //restaurante.removerFormaPagamento(formaPagamento);

        if (!restaurante.removerFormaPagamento(formaPagamento)) {
            throw new NegocioException("Forma de pagamento especificada não está associada ao restaurante");
        }
    }

    @Transactional
    public void adicionarFormaPagamento(Integer restauranteId, Integer formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

//        List<FormaPagamento> formaPagamentos = restaurante.getFormasPagamento();
//        formaPagamentos.add(formaPagamento);
//        restaurante.setFormasPagamento(formaPagamentos);

        if (!restaurante.adicionarFormaPagamento(formaPagamento)) {
            throw new NegocioException("Forma de pagamento já associada com o restaurante");
        }
    }

    @Transactional
    public void associarResponsavel(Integer restauranteId, Integer usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        if (!restaurante.adicionarUsuario(usuario)) {
            throw new NegocioException(String.format("Usuário de código %d já associado com o restaurante de código %d",
                    usuarioId, restauranteId));
        }
    }

    @Transactional
    public void desassociarResponsavel(Integer restauranteId, Integer usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        if (!restaurante.removerUsuario(usuario)) {
            throw new NegocioException(String.format("Usuário de código %d não é responsável do restaurante de código %d",
                    usuarioId, restauranteId));
        }
    }

    @Transactional
    public void ativar(List<Integer> restaurantesIds) {
        restaurantesIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Integer> restaurantesIds) {
        restaurantesIds.forEach(this::inativar);
    }

    public Restaurante buscarOuFalhar(Integer restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
