package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Slf4j
@Service
public class FakeEnvioEmailService implements EnvioEmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem, (Pedido) mensagem.getVariaveis().get("pedido"));
        Map<String, String> infoEmail = new HashMap<>();
        infoEmail.put("Remetente: ", emailProperties.getRemetente());
        infoEmail.put("Destinatário: ", mensagem.getDestinatarios().toString());
        infoEmail.put("Assunto: ", mensagem.getAssunto());
        infoEmail.put("Texto: ", corpo);

        log.info("----------");
        infoEmail.forEach((key, value) -> log.info(key + value));
        log.info("----------");

        log.info(corpo);
    }

    private String processarTemplate(Mensagem mensagem, Pedido pedido) {
        try {
            Context context = new Context(new Locale("pt", "BR"));
            context.setVariable("pedido", pedido);
            context.setVariable("itens", pedido.getItens());
            return templateEngine.process(mensagem.getCorpo(), context);

        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }

    }
}
