package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "https://localhost:8080/erro-de-negocio")
    private String type;

    @Schema(example = "Erro de negócio")
    private String title;

    @Schema(example = "Não existe cadastro de algo com código x")
    private String detail;

    @Schema(example = "Ocorreu um erro interno no sistema. Tente novamente e se o problema persistir, entre em " +
            "contato com o administrador do sistema.")
    private String userMessage;

    @Schema(example = "2024-06-11T02:41:55.3833028")
    private LocalDateTime timeStamp;

    @Schema(description = "Lista de objetos de erros")
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {

        @Schema(example = "Objeto nulo")
        private String name;

        @Schema(example = "O objeto tal não pode ter valor nulo.")
        private String userMessage;
    }
}
