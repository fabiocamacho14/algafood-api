package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Produto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Integer> {

    List<Produto> findByPrecoBetween(BigDecimal precoInicial, BigDecimal precoFinal);
}
