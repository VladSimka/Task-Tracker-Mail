package com.vladsimonenko.tasktrackermail.service.impl;

import com.vladsimonenko.tasktrackermail.event.UserCreatedEvent;
import com.vladsimonenko.tasktrackermail.service.MailService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    public final JavaMailSender mailSender;

    @Override
    @SneakyThrows
    public void sendRegistrationEmail(UserCreatedEvent createdUser, Properties properties) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

        mimeMessageHelper.setSubject("Thank you for registration in our service, "
                                     + createdUser.getUsername().split("@")[0]);

        mimeMessageHelper.setTo(createdUser.getUsername());

        String emailContent = getRegistrationEmailContent(createdUser);
        mimeMessageHelper.setText(emailContent, true);

        mailSender.send(mimeMessage);

    }

    @SneakyThrows
    private String getRegistrationEmailContent(UserCreatedEvent createdUser) {
        StringWriter writer = new StringWriter();

        Map<String, Object> model = new HashMap<>();
        model.put("name", createdUser.getUsername().split("@")[0]);
        configuration.getTemplate("register.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }
}
