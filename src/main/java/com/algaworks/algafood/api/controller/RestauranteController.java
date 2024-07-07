package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@CrossOrigin
@Tag(name = "Controlador de restaurantes", description = "Todos os controladores relativos a restaurantes cadastrados")
public class RestauranteController implements com.algaworks.algafood.api.openapi.controller.RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

//    @Autowired
//    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteModelDisassembler;

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//
//        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//        if ("apenas-nome".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//        } else if ("completo".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(null);
//        }
//
//        return restaurantesWrapper;
//    }

    @Override
    @Operation(summary = "Lista todos os restaurantes")
    @GetMapping
    public List<RestauranteModel> listar(){
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

//    @JsonView(RestauranteView.Resumo.class)
//    @GetMapping(params = "projecao=resumo")
//    public List<RestauranteModel> listarResumido() {
//        return listar();
//    }
//
//    @JsonView(RestauranteView.ApenasNome.class)
//    @GetMapping(params = "projecao=apenas-nome")
//    public List<RestauranteModel> listarApenasNome() {
//        return listar();
//    }

    @Override
    @Operation(summary = "Busca um restaurante" )
    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@Parameter(description = "Id do restaurante") @PathVariable Integer restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @Override
    @Operation(summary = "Adiciona um restaurante")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@Parameter(name = "corpo", description = "Representação de um novo restaurante")
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteModelDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.adicionar(restaurante));

        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Operation(summary = "Atualiza um restaurante")
    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Integer restauranteId, @Valid @RequestBody RestauranteInput restauranteInput) {
//        Restaurante restaurante = restauranteModelDisassembler.toDomainObject(restauranteInput);

        Restaurante restauranteAtualizar = cadastroRestaurante.buscarOuFalhar(restauranteId);

        restauranteModelDisassembler.copyToDomainObject(restauranteInput, restauranteAtualizar);

//        BeanUtils.copyProperties(restaurante, restauranteAtualizar,
//                "id", "formasPagamento", "endereco", "dataCadastro");

        try {
            return restauranteModelAssembler.toModel(cadastroRestaurante.adicionar(restauranteAtualizar));

        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Operation(summary = "Remove um restaurante")
    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer restauranteId) {
            cadastroRestaurante.excluir(restauranteId);
    }

//    @PatchMapping("/{restauranteId}")
//    public Restaurante atualizarParcial(@PathVariable Integer restauranteId,
//                                        @RequestBody Map<String, Object> campos, HttpServletRequest request) {
//
//        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
//
//        merge(campos, restauranteAtual, request);
//        validate(restauranteAtual, "restaurante");
//
//        return atualizar(restauranteId, restauranteAtual);
//    }
//
//    private void validate(Restaurante restaurante, String objectName) {
//        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//
//        validator.validate(restaurante, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidacaoException(bindingResult);
//        }
//    }
//
//    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//            Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
//
//            camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//                field.setAccessible(true);
//
//                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//                System.out.println(field.getName());
//                if (field.getName() != "endereco") {
//                    ReflectionUtils.setField(field, restauranteDestino, novoValor);
//
//                }
//
//            });
//        } catch (IllegalArgumentException e) {
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
//            ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
//            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
//        }
//    }

//    PUT /restaurantes/{id}/ativo
//    DELETE /restaurantes/{id}/ativo
    @Override
    @PutMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Integer restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @Override
    @DeleteMapping("{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Integer restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @Override
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Integer restauranteId) {
        cadastroRestaurante.abrir(restauranteId);
    }

    @Override
    @PutMapping("{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Integer restauranteId) {
        cadastroRestaurante.fechar(restauranteId);
    }

    @Override
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Integer> restaurantesIds) {
        cadastroRestaurante.ativar(restaurantesIds);
    }

    @Override
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Integer> restaurantesIds) {
        try {
            cadastroRestaurante.inativar(restaurantesIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
