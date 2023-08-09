package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
        RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);


//        Alterar o nome do "cadastroCozinha" para "cozinhas"
//        Cozinha cozinha1 = new Cozinha();
//        cozinha1.setNome("Brasileira");
//
//        Cozinha cozinha2 = new Cozinha();
//        cozinha2.setNome("Japonesa");
//
//        cadastroCozinha.adicionar(cozinha1);
//        cadastroCozinha.adicionar(cozinha2);

//        Cozinha cozinhaBuscada = cadastroCozinha.buscarPorID(3);
//        System.out.println(cozinhaBuscada.getId() + " -> " + cozinhaBuscada.getNome());

//        Cozinha cozinha1 = new Cozinha();
//        cozinha1.setId(1);
//        cozinha1.setNome("hihihiha");
//        cadastroCozinha.salvar(cozinha1);

//        Cozinha cozinha1 = new Cozinha();
//        cozinha1.setId(3);
//        cozinhas.remover(cozinha1);

        List<Restaurante> restaurantesLista = restaurantes.findAll();
        for (Restaurante restaurante: restaurantesLista) {
            System.out.println(restaurante.getId() + " -> " + restaurante.getNome() + " -> " + restaurante.getCozinha().getId());
        }
    }
}
