package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormaPagamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", length = 60, nullable = false)
    private String descricao;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "formasPagamento")
//    private List<Restaurante> restaurantes = new ArrayList<>();
}
