package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto fotoProduto) {
        return entityManager.merge(fotoProduto);
    }

    @Override
    public Optional<FotoProduto> findFotoById(Integer restauranteId, Integer produtoId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FotoProduto.class);
        Root<FotoProduto> root = criteriaQuery.from(FotoProduto.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("produto").get("id"), produtoId),
                criteriaBuilder.equal(root.get("produto").get("restaurante").get("id"), restauranteId)
        );

        TypedQuery<FotoProduto> fotoProdutoTypedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return Optional.ofNullable(fotoProdutoTypedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(FotoProduto fotoProduto) {
        entityManager.remove(fotoProduto);
    }


}
