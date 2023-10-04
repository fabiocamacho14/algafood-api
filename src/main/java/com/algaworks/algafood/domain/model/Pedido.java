package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(name = "data_criacao", columnDefinition = "datetime", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_atualizacao", columnDefinition = "datetime")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "data_cancelamento", columnDefinition = "datetime")
    private OffsetDateTime dataCancelamento;

    @Column(name = "data_entrega", columnDefinition = "datetime")
    private OffsetDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Embedded
    private Endereco enderecoEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_FORMA_PAGAMENTO"), nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_USUARIO"), nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurante_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_RESTAURANTE"), nullable = false)
    private Restaurante restaurante;

    public void calcularPedido() {
        System.out.println("-------------------------------------");
        getItens().forEach(ItemPedido::calcularPrecoTotal);
        for (ItemPedido itemPedido: getItens()){
            System.out.println("-------------------------------------");
            System.out.println(itemPedido.toString());
        };
//        List<BigDecimal> precos = itens.stream().
//                map(itemPedido -> (itemPedido.getProduto().getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())))).toList();
//        BigDecimal soma = precos.stream().
//                reduce(BigDecimal.ZERO, BigDecimal::add);

//        subtotal = itens.stream()
//                .map(ItemPedido::getPrecoTotal)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
        subtotal = BigDecimal.ZERO;
        for (ItemPedido itemPedido: itens) {
            subtotal = getSubtotal().add(itemPedido.getProduto().getPreco());
        }
        valorTotal = subtotal.add(restaurante.getTaxaFrete());
    }
}