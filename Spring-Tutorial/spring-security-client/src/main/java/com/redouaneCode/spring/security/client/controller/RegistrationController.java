package com.redouaneCode.spring.security.client.controller;

import com.redouaneCode.spring.security.client.entity.User;
import com.redouaneCode.spring.security.client.entity.VerificationToken;
import com.redouaneCode.spring.security.client.event.RegistrationCompleted;
import com.redouaneCode.spring.security.client.model.PasswordModel;
import com.redouaneCode.spring.security.client.model.UserModel;
import com.redouaneCode.spring.security.client.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService service;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = service.registerUser(userModel);
            publisher.publishEvent(new RegistrationCompleted(user, applicationUrl(request)));
        return "SUCCESS";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result= service.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verifies successfully";
        }

        return "Bad User";
    }


    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest http){
        VerificationToken verificationToken = service.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(http), verificationToken.getToken());
        return "Verification token resent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel){
        User user = service.findUserByEmail(passwordModel.getEmail());
        if(user!=null){
            String token = UUID.randomUUID().toString();
            service.createPasswordResetTokenForUser(user, token);

        }
        return "password completed";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/verifyRegistration?token=" + token;
        log.info("Click the link to verfify your acount: {}", url);

    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
    }
}
