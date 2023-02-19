package com.redouaneCode.spring.security.client.service;

import com.redouaneCode.spring.security.client.entity.PasswordToken;
import com.redouaneCode.spring.security.client.entity.User;
import com.redouaneCode.spring.security.client.entity.VerificationToken;
import com.redouaneCode.spring.security.client.model.UserModel;
import com.redouaneCode.spring.security.client.repository.PasswordTokenRepository;
import com.redouaneCode.spring.security.client.repository.UserRepository;
import com.redouaneCode.spring.security.client.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Override
    public User registerUser(UserModel userModel) {
        User user = User.builder()
                .userName(userModel.getUserName())
                .LastName(userModel.getLastName())
                .email(userModel.getEmail())
                .role("user")
                .password(passwordEncoder.encode(userModel.getPassword()))
                .build();

        repository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);

    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if(verificationToken == null){
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if((verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()) <= 0){
            tokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabeled(true);
        repository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        tokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordToken passwordToken = new PasswordToken(user, token);
        passwordTokenRepository.save(passwordToken);

    }
}
