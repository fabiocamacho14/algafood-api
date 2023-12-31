package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "preco_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoTotal;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "FK_ITEM_PEDIDO_PRODUTO"), nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_ITEM_PEDIDO"), nullable = false)
    private Pedido pedido;

    public void calcularPrecoTotal() {
        precoTotal = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

}
