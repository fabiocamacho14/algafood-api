package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.assembler.UsuarioPutInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioPutSenhaInputDisassembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioPutInput;
import com.algaworks.algafood.api.model.input.UsuarioPutSenhaInput;
import com.algaworks.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPutInputDisassembler usuarioPutInputDisassembler;

    @Autowired
    private UsuarioPutSenhaInputDisassembler usuarioPutSenhaInputDisassembler;

    @Override
    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Integer usuarioId) {
        return usuarioModelAssembler.toModel(cadastroUsuario.buscarOuFalhar(usuarioId));
    }

    @Override
    @GetMapping
    public Collection<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionList(usuarioRepository.findAll());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioInserir = usuarioInputDisassembler.toDomainObject(usuarioInput);
        return usuarioModelAssembler.toModel(cadastroUsuario.adicionar(usuarioInserir));
    }

    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer usuarioId) {
        cadastroUsuario.remover(usuarioId);
    }

    @Override
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioPutInput usuarioPutInput) {
        Usuario usuarioAtualizar = cadastroUsuario.buscarOuFalhar(usuarioId);

        usuarioPutInputDisassembler.copyToDomainObject(usuarioPutInput, usuarioAtualizar);
        return usuarioModelAssembler.toModel(cadastroUsuario.adicionar(usuarioAtualizar));
    }

    @Override
    @PutMapping("{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioPutSenhaInput usuarioPutSenhaInput) {
        Usuario usuarioAtualizar = cadastroUsuario.buscarOuFalhar(usuarioId);

        if (!(usuarioPutSenhaInput.getSenhaAtual().equals(usuarioAtualizar.getSenha()))) {
            throw new NegocioException("Senha atual não coincide com a senha do usuário.");
        }

        usuarioPutSenhaInputDisassembler.copyToDomainObject(usuarioPutSenhaInput, usuarioAtualizar);


        cadastroUsuario.adicionar(usuarioAtualizar);
    }
}
