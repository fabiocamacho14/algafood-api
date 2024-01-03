package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto, InputStream inputStream) {
        Integer restauranteId = fotoProduto.getProduto().getRestaurante().getId();
        Integer produtoId = fotoProduto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(fotoProduto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente =  produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
//        excluir foto se existir
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        fotoProduto.setNomeArquivo(nomeNovoArquivo);
        fotoProduto = produtoRepository.save(fotoProduto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(inputStream)
                .build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return fotoProduto;
    }
}
