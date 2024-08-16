package com.algaworks.algafood.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Links")
public class LinksModelOpenApi {

    private LinkModel link;

    @Getter
    @Setter
    @Schema(name = "Link")
    public class LinkModel {
        private String href;
        private boolean templated;
    }
}
