package com.vladsimonenko.tasktrackermail.service;

import com.vladsimonenko.tasktrackermail.event.UserCreatedEvent;

import java.util.Properties;

public interface MailService {
    void sendRegistrationEmail(UserCreatedEvent createdUser, Properties properties);
}
