package com.vladsimonenko.tasktrackermail.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatedEvent {
    private Long id;

    private String username;
}