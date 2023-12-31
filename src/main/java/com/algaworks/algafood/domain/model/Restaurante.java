package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

//@ValorZeroIncluiDescricao(valor = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<Produto> produtos = new HashSet<>();

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.TRUE;

    @ManyToMany()
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id", foreignKey = @ForeignKey(name = "FK_RESTAURANTE_USUARIO_RESPONSAVEL_RESTAURANTE")),
            inverseJoinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_RESTAURANTE_USUARIO_RESPONSAVEL_USUARIO")))
    private Set<Usuario> responsaveis;

    public void ativar() {
        ativo = Boolean.TRUE;
    }

    public void inativar() {
        ativo = Boolean.FALSE;
    }

    public void abrir() {
        aberto = Boolean.TRUE;
    }

    public void fechar() {
        aberto = Boolean.FALSE;
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().add(formaPagamento);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().remove(formaPagamento);
    }

    public boolean adicionarUsuario(Usuario usuario) {
        return getResponsaveis().add(usuario);
    }

    public boolean removerUsuario(Usuario usuario) {
        return getResponsaveis().remove(usuario);
    }

    public boolean verificarSeContemFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().contains(formaPagamento);
    }
}
