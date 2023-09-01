package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Integer> {
}
