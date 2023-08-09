package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Integer>,
        RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Integer cozinha);
//    @Query("select r from Restaurante r where r.nome like %:nome% and r.cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Integer cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Integer id);
}
