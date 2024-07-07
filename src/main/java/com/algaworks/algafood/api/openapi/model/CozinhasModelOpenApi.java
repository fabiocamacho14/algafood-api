package com.algaworks.algafood.api.openapi.model;

import com.algaworks.algafood.api.model.CozinhaModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Tag(name = "CozinhasModel")
public class CozinhasModelOpenApi extends PagedModelOpenApi<CozinhaModel>{

}
