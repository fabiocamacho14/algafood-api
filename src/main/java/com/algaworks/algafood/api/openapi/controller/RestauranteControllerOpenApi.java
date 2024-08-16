package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteBasicoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Controlador de restaurantes", description = "Todos os controladores relativos a restaurantes cadastrados")
public interface RestauranteControllerOpenApi {

    @Operation(summary = "Lista todos os restaurantes", description = "Lista todos os restaurantes cadastrados no " +
            "banco de dados do sistema",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = RestauranteBasicoModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                                operationRef = "https://localhost:8080/restaurantes"
                        )
                })
            }
    )
    CollectionModel<RestauranteBasicoModel> listar();

    @Operation(summary = "Busca um restaurante", description = "Busca um restaurante de código específico cadastrado " +
            "no banco de dados do sistema",
            responses = {
                @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
                @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = Problem.class)
                )),
                @ApiResponse(responseCode = "200", description = "Restaurante encontrado", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = RestauranteModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}",
                                parameters = {
                                    @LinkParameter(name = "Id de Restaurante", expression = "restauranteId")
                                }
                        ),
                        @Link(name = "collection", description = "Collection Link",
                                operationRef = "https://localhost:8080/restaurantes"
                        ),
                        @Link(name = "inativar", description = "Inativar restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                                parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "ativar", description = "Ativar restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "responsaveis", description = "Lista responsáveis pelo restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/responsaveis",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "formas-pagamento", description = "Lista formas de pagamento do restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/formas-pagamento",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "abrir", description = "Abre restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/abertura",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "fechar", description = "Fecha restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/fechamento",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        )
                })
            }
    )
    RestauranteModel buscar(@Parameter(description = "Id do restaurante")  @PathVariable Integer restauranteId);

    @Operation(summary = "Adiciona um restaurante", description = "Adiciona um restaurante no banco de dados do " +
            "sistema",
            responses = {
                @ApiResponse(responseCode = "201", description = "Restaurante adicionado", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = RestauranteModel.class)
                ), links = {
                        @Link(name = "self", description = "Self Link",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}",
                                parameters = {
                                        @LinkParameter(name = "Id de Restaurante", expression = "restauranteId")
                                }
                        ),
                        @Link(name = "collection", description = "Collection Link",
                                operationRef = "https://localhost:8080/restaurantes"
                        ),
                        @Link(name = "inativar", description = "Inativar restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "ativar", description = "Ativar restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "responsaveis", description = "Lista responsáveis pelo restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/responsaveis",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "formas-pagamento", description = "Lista formas de pagamento do restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/formas-pagamento",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "abrir", description = "Abre restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/abertura",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        ),
                        @Link(name = "fechar", description = "Fecha restaurante",
                                operationRef = "https://localhost:8080/restaurantes/{restauranteId}/fechamento",
                                parameters = {
                                        @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                                }
                        )
                })
            }
    )
    RestauranteModel adicionar(@Parameter(name = "corpor", description = "corpo de representação de um restaurante")
            @RequestBody @Valid RestauranteInput restauranteInput);

    @Operation(summary = "Atualiza um restaurante", description = "Atualiza um restaurante de código específico " +
            "cadastrado no banco de dados do sistema",
            responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = RestauranteModel.class)
            ), links = {
                    @Link(name = "self", description = "Self Link",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}",
                            parameters = {
                                    @LinkParameter(name = "Id de Restaurante", expression = "restauranteId")
                            }
                    ),
                    @Link(name = "collection", description = "Collection Link",
                            operationRef = "https://localhost:8080/restaurantes"
                    ),
                    @Link(name = "inativar", description = "Inativar restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "ativar", description = "Ativar restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/ativo",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "responsaveis", description = "Lista responsáveis pelo restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/responsaveis",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "formas-pagamento", description = "Lista formas de pagamento do restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/formas-pagamento",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "abrir", description = "Abre restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/abertura",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    ),
                    @Link(name = "fechar", description = "Fecha restaurante",
                            operationRef = "https://localhost:8080/restaurantes/{restauranteId}/fechamento",
                            parameters = {
                                    @LinkParameter(name = "Id de restaurante", expression = "{restauranteId}")
                            }
                    )
            })
    })
    RestauranteModel atualizar(@PathVariable Integer restauranteId, @Valid @RequestBody RestauranteInput restauranteInput);

    @Operation(summary = "Remove um restaurante", description = "Remove um restaurante de código específico " +
            "cadastrado no banco de dados do sistema", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            ))
    })
    void remover(@PathVariable Integer restauranteId);

    @Operation(summary = "Ativa um restaurante", description = "Atualiza o estado de um restaurante inativado para " +
            "ativado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void ativar(@PathVariable Integer restauranteId);

    @Operation(summary = "Desativa um restaurante", description = "Atualiza o estado de um restaurante inativado para" +
            "desativado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    ResponseEntity<Void> inativar(@PathVariable Integer restauranteId);

    @Operation(summary = "Abre um restaurante", description = "Atualiza o estado de um restaurante fechado para" +
            "aberto", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Restaurante já encontra-se aberto", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    ResponseEntity<Void> abrir(@PathVariable Integer restauranteId);

    @Operation(summary = "Fecha um restaurante", description = "Atualiza o estado de um restaurante aberto para" +
            "fechado", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "Restaurante já encontra-se fechado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
    })
    ResponseEntity<Void> fechar(@PathVariable Integer restauranteId);

    @Operation(summary = "Ativa vários restaurantes", description = "Atualiza o estado de vários restaurantes " +
            "inativados para ativados", responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void ativarMultiplos(@RequestBody List<Integer> restaurantesIds);

    @Operation(summary = "Inativa vários restaurantes", description = "Atualiza o estado de um restaurante inativado " +
            "para ativado",
            responses = {
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),
            @ApiResponse(responseCode = "400", description = "ID de restaurante inválido", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = Problem.class)
            )),

    })
    void inativarMultiplos(@RequestBody List<Integer> restaurantesIds);
}
