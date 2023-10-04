package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

//    @OneToMany(mappedBy = "estado")
//    @JsonManagedReference
//    private List<Cidade> cidades;
}
