package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "formasPagamento")
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

    @Schema(description = "Código da forma de pagamento", example = "1")
    private Integer id;

    @Schema(description = "Descrição da forma de pagamento", example = "Cartão de débito")
    private String descricao;

    @Schema(hidden = true)
    private Links _links;
}
