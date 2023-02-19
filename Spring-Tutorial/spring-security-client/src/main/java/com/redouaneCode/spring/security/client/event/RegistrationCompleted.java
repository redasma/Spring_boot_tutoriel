package com.redouaneCode.spring.security.client.event;

import com.redouaneCode.spring.security.client.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class RegistrationCompleted extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    public RegistrationCompleted( User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl =applicationUrl;
    }
}
