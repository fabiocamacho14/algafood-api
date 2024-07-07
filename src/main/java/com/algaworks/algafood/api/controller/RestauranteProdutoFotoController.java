package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController implements com.algaworks.algafood.api.openapi.controller.RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Override
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
        fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
        fotoProduto.setContentType(arquivo.getContentType());
        fotoProduto.setTamanho((int) arquivo.getSize());
        fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto, arquivo.getInputStream());
        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Integer restauranteId, @PathVariable Integer produtoId) {
        cadastroProdutoService.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId);
        return fotoProdutoModelAssembler.toModel(catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId));
    }

//    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    @Override
    @GetMapping
    public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Integer restauranteId,
                                                          @PathVariable Integer produtoId,
                                                          @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            cadastroProdutoService.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId);
            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypeAceitos = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypeAceitos);

            InputStream arquivoFoto = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(arquivoFoto));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping
    public void excluirFoto(@PathVariable Integer restauranteId,
                            @PathVariable Integer produtoId) {

        cadastroProdutoService.buscarProdutoEmRestauranteEspecifico(restauranteId, produtoId);
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        catalogoFotoProdutoService.excluir(fotoProduto);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypeList) throws HttpMediaTypeNotAcceptableException {
        boolean compativel = mediaTypeList.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeList);
        }
    }
}