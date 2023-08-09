package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Integer estadoId) {
        return estadoModelAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@Valid @RequestBody EstadoInput estado) {
        Estado estadoInserir = estadoInputDisassembler.toDomainObject(estado);
        return estadoModelAssembler.toModel(cadastroEstado.adicionar(estadoInserir));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Integer estadoId, @Valid @RequestBody EstadoInput estado) {
        cadastroEstado.buscarOuFalhar(estadoId);
        Estado estadoAtualizar = estadoInputDisassembler.toDomainObject(estado);

        return estadoModelAssembler.toModel(cadastroEstado.adicionar(estadoAtualizar));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer estadoId) {
            cadastroEstado.excluir(estadoId);
    }
}
