package com.algaworks.algafood.infrastructure.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

        var functionTz = criteriaBuilder.function(
                "convert_tz",
                Date.class,
                root.get("dataCriacao"),
                criteriaBuilder.literal("+00:00"),
                criteriaBuilder.literal(timeOffSet)
                );

        var functionDate =  criteriaBuilder.function("date", Date.class, functionTz);

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
