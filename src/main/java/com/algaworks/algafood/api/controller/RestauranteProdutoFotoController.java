package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId,
                                      @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

//        String nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
//
//        Path arquivoFoto = Path.of("D:\\testejava", nomeArquivo);
//
//        System.out.println(fotoProdutoInput.getDescricao());
//        System.out.println(fotoProdutoInput.getArquivo().getContentType());
//        System.out.println(fotoProdutoInput.getArquivo().getOriginalFilename());
//
//        System.out.println(fotoProdutoInput.getArquivo().getName());
//        System.out.println(arquivoFoto);
//        System.out.println(fotoProdutoInput.getArquivo().getContentType());
//
//        try {
//            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        Produto produto = cadastroProdutoService.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setProduto(produto);
        fotoProduto.setDescricao(fotoProduto.getDescricao());
        fotoProduto.setContentType(arquivo.getContentType());
        fotoProduto.setTamanho((int) arquivo.getSize());
        fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto, arquivo.getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }
    
}