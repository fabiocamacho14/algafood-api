package com.algaworks.algafood.api;

import com.algaworks.algafood.api.controller.*;
import org.mockito.Mockito;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToPedidos() {
        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
    }

    public Link linkToRestaurantes(Integer restauranteId) {
        return linkTo(methodOn(RestauranteController.class)
                .buscar(restauranteId))
                .withSelfRel();
    }

    public Link linkToUsuarios(Integer usuarioId) {
        return linkTo(methodOn(UsuarioController.class)
                .buscar(usuarioId))
                .withSelfRel();
    }

    public Link linkToCidades() {
        return linkTo(methodOn(CidadeController.class)
                .listar()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToCidadesCollection() {
        return linkToCidades().withSelfRel();
    }

    public Link linkToEstados(Integer estadoId) {
        return linkTo(methodOn(EstadoController.class)
                .buscar(estadoId)).withSelfRel();
    }

    public Link linkToPedidosCollection() {
        return linkTo(PedidoController.class)
                .withSelfRel();
    }

    public Link linkToUsuarios() {
        return linkTo(UsuarioController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToUsuariosCollection() {
        return linkToUsuarios()
                .withSelfRel();
    }

    public Link linkToUsuariosGrupos(Integer usuarioId) {
        return linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
                .listarGrupos(usuarioId))
                .withRel("grupos-usuarios");
    }

    public Link linkToUsuariosGruposCollection(Integer restauranteId) {
        return linkTo(methodOn(RestauranteResponsavelController.class)
                .listar(restauranteId))
                .withSelfRel();
    }

    public Link linkToEstados() {
        return linkTo(EstadoController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToEstadosCollection() {
        return linkTo(EstadoController.class)
                .withSelfRel();
    }

    public Link linkToCozinhas() {
        return linkTo(CozinhaController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToFormaPagamentos(Integer formaPagamentoId) {
        return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(formaPagamentoId, Mockito.mock(ServletWebRequest.class)))
                .withSelfRel();
    }

    public Link linkToCidades(Integer cidadeId) {
        return linkTo(methodOn(CidadeController.class)
                .buscar(cidadeId))
                .withSelfRel();
    }

    public Link linkToPedidosCol() {
        return linkTo(PedidoController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToProdutos(Integer restauranteId, Integer produtoId) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(restauranteId, produtoId))
                .withSelfRel();
    }

    public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .confirmar(codigoPedido))
                .withRel(rel);
    }

    public Link linkToEntregaPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .entregar(codigoPedido))
                .withRel(rel);
    }

    public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class)
                .cancelar(codigoPedido))
                .withRel(rel);
    }

    public Link linkToCozinhas(Integer cozinhaId) {
        return linkTo(methodOn(CozinhaController.class)
                .buscar(cozinhaId))
                .withSelfRel();
    }

    public Link linkToRestaurantesCollection() {
        return linkTo(RestauranteController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToInativarRestaurantes(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .inativar(restauranteId))
                .withRel(rel);
    }

    public Link linkToFecharRestaurantes(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .fechar(restauranteId))
                .withRel(rel);
    }

    public Link linkToAbrirRestaurantes(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class)
                .abrir(restauranteId))
                .withRel(rel);
    }

    public Link linkToRestaurantesResponsaveis(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteResponsavelController.class)
                .listar(restauranteId))
                .withRel(rel);
    }

    public Link linkToFormasPagamentoRestaurantes(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .listar(restauranteId))
                .withRel(rel);
    }

    public Link linkToFormasPagamento() {
        return linkTo(methodOn(FormaPagamentoController.class)
                .listar(Mockito.mock(ServletWebRequest.class)))
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToFormasPagamentoCollection() {
        return linkTo(FormaPagamentoController.class).withSelfRel();
    }

    public Link linkToDesassociacaoFormasPagamento(Integer restauranteId, Integer formaPagamentoId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .desassociarFormaPagamento(restauranteId, formaPagamentoId))
                .withRel(rel);
    }

    public Link linkToAssociacaoFormasPagamento(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .associarFormaPagamento(restauranteId, null))
                .withRel(rel);
    }

    public Link linkToDesassociacaoResponsaveis(Integer restauranteId, Integer usuarioId, String rel) {
        return linkTo(methodOn(RestauranteResponsavelController.class)
                .desassociarResposavel(restauranteId, usuarioId))
                .withRel(rel);
    }

    public Link linkToAssociacaoResponsaveis(Integer restauranteId, String rel) {
        return linkTo(methodOn(RestauranteResponsavelController.class)
                .associarResposavel(restauranteId, null))
                .withRel(rel);
    }

    public Link linkToProdutos(Integer restauranteId, Boolean incluirInativos) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .listar(restauranteId, incluirInativos))
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToProdutosCollection(Integer restauranteId) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .listar(restauranteId, null))
                .withSelfRel();
    }

    public Link linkToFotosProduto(Integer restauranteId, Integer produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoFotoController.class)
                .buscar(restauranteId, produtoId))
                .withRel(rel);
    }

    public Link linkToGrupos() {
        return linkTo(GrupoController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToPermissoesGrupos(Integer grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissoesController.class, grupoId)
                .listar(grupoId))
                .withRel(rel);
    }

    public Link linkToGruposCollection() {
        return linkTo(GrupoController.class)
                .withSelfRel();
    }

    public Link linkToPermissao() {
        return linkTo(PermissaoController.class)
                .withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToPermissoesCollection() {
        return linkTo(PermissaoController.class)
                .withSelfRel();
    }

    public Link linkToDesassociarPermissao(Integer grupoId, Integer permissaoId, String rel) {
        return linkTo(methodOn(GrupoPermissoesController.class)
                .desassociarPermissao(grupoId, permissaoId))
                .withRel(rel);
    }

    public Link linkToAssociarPermissao(Integer grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissoesController.class)
                .associarPermissao(grupoId, null))
                .withRel(rel);
    }

    public Link linkToDesassociarGrupos(Integer usuarioId, Integer grupoId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).desassociarGrupo(usuarioId, grupoId)).withRel(rel);
    }

    public Link linkToAssociarGrupos(Integer usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).associarGrupo(usuarioId, null)).withRel(rel);
    }
 }
