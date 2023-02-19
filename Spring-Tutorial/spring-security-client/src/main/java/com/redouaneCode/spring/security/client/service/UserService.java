package com.redouaneCode.spring.security.client.service;

import com.redouaneCode.spring.security.client.entity.User;
import com.redouaneCode.spring.security.client.entity.VerificationToken;
import com.redouaneCode.spring.security.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);
}
