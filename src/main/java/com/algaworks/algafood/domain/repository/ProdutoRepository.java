package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Integer>, ProdutoRepositoryQueries {

    List<Produto> findByPrecoBetween(BigDecimal precoInicial, BigDecimal precoFinal);

    @Query("select p from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);

//    @Query("select f from FotoProduto f join f.produto p where (f.produto.id = :produtoId and p.restaurante.id = " +
//            ":restauranteId)")
//    Optional<FotoProduto> findFotoById(Integer restauranteId, Integer produtoId);
}
