package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class SandboxEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem, (Pedido) mensagem.getVariaveis().get("pedido"));

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage , "UTF-8");
            mimeMessageHelper.setFrom(emailProperties.getRemetente());
//            mimeMessageHelper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            mimeMessageHelper.setTo("fcamacho933@gmail.com");
            mimeMessageHelper.setSubject(mensagem.getAssunto());
            mimeMessageHelper.setText(corpo, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
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
