package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
//@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cozinhas")
@Tag(name = "Controlador de cozinhas", description = "Todos os controladores relativos a cozinhas cadastradas")
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

//    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Override
    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CozinhaModel> cozinhaModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());

        Page<CozinhaModel> cozinhaModelPage = new PageImpl<>(cozinhaModel, pageable, cozinhasPage.getTotalElements());

        return cozinhaModelPage;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @GetMapping("/{cozinhaId}")
//    public ResponseEntity<Cozinha> buscar(@PathVariable Integer cozinhaId) {
//        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
////        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
////        return ResponseEntity.ok(cozinha);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
//    }

    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Integer cozinhaId) {
        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));

//        Optional<Cozinha> cozinha =  cozinhaRepository.findById(cozinhaId);

//        if (cozinha.isPresent()) {
//            return ResponseEntity.ok(cozinha.get());
//        }
//
////        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        return ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        return cozinhaModelAssembler.toModel(cadastroCozinha.adicionar(cozinha));
    }


    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Integer cozinhaId, @Valid @RequestBody CozinhaInput cozinha) {
        cadastroCozinha.buscarOuFalhar(cozinhaId);

        Cozinha cozinhaAtualizar = cozinhaInputDisassembler.toDomainObject(cozinha);
        return cozinhaModelAssembler.toModel(cadastroCozinha.adicionar(cozinhaAtualizar));
    }

//    @DeleteMapping("/{cozinhaId}")
//    public ResponseEntity<?> deletar(@PathVariable Integer cozinhaId) {
////        try {
////            Cozinha cozinhaRemocao = cozinhaRepository.buscar(cozinhaId);
////            if (cozinhaRemocao != null) {
////                cozinhaRepository.remover(cozinhaRemocao);
////                return ResponseEntity.noContent().build();
////            }
////
////            return ResponseEntity.notFound().build();
////        } catch (DataIntegrityViolationException e) {
////            return ResponseEntity.status(HttpStatus.CONFLICT).build();
////        }
//        try {
//            cadastroCozinha.excluir(cozinhaId);
//            return ResponseEntity.noContent().build();
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//
////        } catch (EntidadeNaoEncontradaException e) {
////            return ResponseEntity.notFound().build();
//        }
//    }

    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }
}
