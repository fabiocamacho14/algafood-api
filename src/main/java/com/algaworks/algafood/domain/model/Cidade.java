package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", foreignKey = @ForeignKey(name = "FK_CIDADE_ESTADO"), nullable = false)
    private Estado estado;
}
