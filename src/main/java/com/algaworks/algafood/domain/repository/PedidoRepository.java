package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Integer> {

//    @Query("from Pedido where codigo = :codigo")
    Optional<Pedido> findByCodigo(String codigo);

    @Query("select p from Pedido p join fetch p.restaurante r join fetch p.usuario join fetch r.cozinha")
    List<Pedido> findAll();
}
