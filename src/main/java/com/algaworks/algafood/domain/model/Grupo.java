package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao",
            joinColumns = @JoinColumn(name = "grupo_id", foreignKey = @ForeignKey(name = "FK_GRUPO_PERMISSAO_GRUPO")),
            inverseJoinColumns = @JoinColumn(name = "permissao_id", foreignKey = @ForeignKey(name = "FK_GRUPO_PERMISSAO_PERMISSAO"))
    )
    private List<Permissao> permissoes = new ArrayList<>();

    public boolean adicionarPermissao(Permissao permissao) {
        return permissoes.add(permissao);
    }

    public boolean removerPermissao(Permissao permissao) {
        return permissoes.remove(permissao);
    }
}
