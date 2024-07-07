package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @Schema(description = "CEP do endereço", example = "01132-023")
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Rua Cardeal Arcoverde")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "984")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apartamento 78")
    private String complemento;

    @Schema(description = "Bairro do endereço", example = "Alto de Pinheiros")
    private String bairro;
    
    private CidadeResumoModel cidade;
}
