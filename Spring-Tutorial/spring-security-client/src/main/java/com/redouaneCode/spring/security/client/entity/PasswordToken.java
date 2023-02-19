package com.redouaneCode.spring.security.client.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordToken {

    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name= "FK_USER_VERIFY_TOKEN"))
    private User user;

    public PasswordToken(User user, String token) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = (Date) calculateExpirationDate(EXPIRATION_TIME);
    }


    public PasswordToken(String token) {
        super();
        this.token = token;
        this.expirationTime = (Date) calculateExpirationDate(EXPIRATION_TIME);
    }
    private Date calculateExpirationDate(int expirationTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
