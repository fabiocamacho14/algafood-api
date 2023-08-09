package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//@ValorZeroIncluiDescricao(valor = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

//    @DecimalMin("1")
//    @TaxaFrete
//    @Multiplo(numero = 5)
    @Column(name = "taxa_frete", nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaFrete;

    @Embedded
    private Endereco endereco;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
//    private LocalDateTime dataCadastro;
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
//    private LocalDateTime dataAtualizacao;
    private OffsetDateTime dataAtualizacao;

    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "cozinha_id", foreignKey = @ForeignKey(name = "FK_RESTAURANTE_COZINHA"), nullable = false)
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id", foreignKey = @ForeignKey(name = "FK_RESTAURANTE_FORMA_PAGAMENTO_RESTAURANTE")),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id", foreignKey = @ForeignKey(name = "FK_RESTAURANTE_FORMA_PAGAMENTO_FORMA_PAGAMENTO"))
    )
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
}
