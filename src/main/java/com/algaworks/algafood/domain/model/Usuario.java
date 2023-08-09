package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_USUARIO_GRUPO_USUARIO")),
            inverseJoinColumns = @JoinColumn(name = "grupo_id", foreignKey = @ForeignKey(name = "FK_USUARIO_GRUPO_GRUPO"))
    )
    private List<Grupo> grupos = new ArrayList<>();
}
