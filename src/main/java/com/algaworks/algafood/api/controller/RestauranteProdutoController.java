package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Integer restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return produtoModelAssembler.toCollectionModel(restaurante.getProdutos());
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId) {
        return produtoModelAssembler.toModel(cadastroProduto.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId));
    }

    @PostMapping
    public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        return produtoModelAssembler.toModel(cadastroProduto.adicionar(produto, restauranteId));
    }

//    @DeleteMapping("/{produtoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void excluir(@PathVariable Integer produtoId, @PathVariable Integer restauranteId) {
//        cadastroProduto.remover(produtoId, restauranteId);
//    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Integer restauranteId, @PathVariable Integer produtoId) {
        cadastroRestaurante.buscarOuFalhar(restauranteId);
        Produto produtoAtualizar = cadastroProduto.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtualizar);
        return produtoModelAssembler.toModel(cadastroProduto.adicionar(produtoAtualizar, restauranteId));
    }
}
