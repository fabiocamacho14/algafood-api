package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JpaController {

    private CozinhaRepository cozinhas;

    public JpaController(CozinhaRepository cozinhaRepository) {
        this.cozinhas = cozinhaRepository;

        System.out.println("JpaController: " + this);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("Hello chamado!");
        List<Cozinha> lista = cozinhas.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Cozinha cozinha : lista) {
            stringBuilder.append(cozinha.getId() + " -> " + cozinha.getNome() + "<br>");
        }

        return stringBuilder.toString();
    }
}
