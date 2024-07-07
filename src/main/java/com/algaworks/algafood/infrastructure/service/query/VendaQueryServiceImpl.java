package com.algaworks.algafood.infrastructure.service.query;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter, String timeOffSet) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendaDiaria> criteriaQuery = criteriaBuilder.createQuery(VendaDiaria.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        var functionDate = criteriaBuilder.function("DATE", Date.class, root.get("dataCriacao"));

        criteriaQuery.select(
                criteriaBuilder.construct(VendaDiaria.class,
                functionDate,
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.sum(root.get("valorTotal")))
        );

        criteriaQuery.groupBy(functionDate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
