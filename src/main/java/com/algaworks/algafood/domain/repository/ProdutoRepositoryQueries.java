package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FotoProduto;

import java.util.Optional;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto fotoProduto);

    Optional<FotoProduto> findFotoById(Integer restauranteId, Integer produtoId);

    void delete(FotoProduto fotoProduto);
}
