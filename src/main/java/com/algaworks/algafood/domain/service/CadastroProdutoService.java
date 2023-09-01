package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Transactional
    public Produto adicionar(Produto produto, Integer restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        cadastroRestaurante.buscarOuFalhar(restaurante.getId());
        produto.setAtivo(Boolean.TRUE);
        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

//    @Transactional
//    public void remover(Integer produtoId, Integer restauranteId) {
//        Produto produto = buscarOuFalhar(produtoId);
//        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
//
//        if (!restaurante.getProdutos().contains(produto)) {
//            throw new NegocioException(String.format("Produto de id %d não existente no restaurante de id %d",
//                    produtoId, restauranteId));
//        }
//
//        try {
//            produtoRepository.deleteById(produtoId);
//            restaurante.getProdutos().remove(produto);
//            Set<Produto> produtos = restaurante.getProdutos();
//            restaurante.setProdutos(produtos);
//            produtoRepository.flush();
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//                    String.format("Produto de id %d não pode ser removido, pois está em uso", produtoId));
//        }
//    }

    public Produto buscarProdutoEmRestauranteEspecifico(Integer restauranteId, Integer produtoId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        Set<Produto> produtos = restaurante.getProdutos();
        Produto produto = buscarOuFalhar(produtoId);

        if (produtos.contains(produto)) {
            return produto;
        } else {
            throw new NegocioException(String.format("Produto de id %d não existente no restaurante %d",
                    produtoId, restauranteId));
        }
    }



    public Produto buscarOuFalhar(Integer produtoId) {
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
