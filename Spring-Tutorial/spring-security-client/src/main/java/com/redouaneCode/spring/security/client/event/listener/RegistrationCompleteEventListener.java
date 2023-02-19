package com.redouaneCode.spring.security.client.event.listener;

import com.redouaneCode.spring.security.client.entity.User;
import com.redouaneCode.spring.security.client.event.RegistrationCompleted;
import com.redouaneCode.spring.security.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleted> {

    @Autowired
    private UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompleted event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verfify your acount: {}", url);
    }
}
