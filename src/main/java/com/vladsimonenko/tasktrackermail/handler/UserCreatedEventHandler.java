package com.vladsimonenko.tasktrackermail.handler;

import com.vladsimonenko.tasktrackermail.event.UserCreatedEvent;
import com.vladsimonenko.tasktrackermail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedEventHandler {
    private final MailService mailService;


    @KafkaListener(topics = "user-created-events-topic")
    public void handle(UserCreatedEvent userCreatedEvent) {
        log.info("Receive this new user:{}", userCreatedEvent.getUsername());
        mailService.sendRegistrationEmail(userCreatedEvent, new Properties());
    }
}
