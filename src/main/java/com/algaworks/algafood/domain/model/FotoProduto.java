package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer id;

    @Column(name = "nome_arquivo", length = 150, nullable = false)
    private String nomeArquivo;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "content_type", length = 80, nullable = false)
    private String contentType;

    @Column(name = "tamanho", nullable = false)
    private Integer tamanho;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
}
