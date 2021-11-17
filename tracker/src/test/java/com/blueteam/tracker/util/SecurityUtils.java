package com.blueteam.tracker.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SecurityUtils {

    private SecurityUtils() {

    }

    public static String getAdminJWT() {
        long now = new Date().getTime();
        Date expiresAt = new Date(now + (125 * 60 * 1000));
        return createToken("admin", "ADMIN", expiresAt);
    }

    public static String getUserJWT() {
        long now = new Date().getTime();
        Date expiresAt = new Date(now + (125 * 60 * 1000));
        return createToken("user", "USER", expiresAt);
    }

    private static String createToken(String subject, String authorities, Date expiresAt) {
        return JWT.create()
                .withSubject(subject)
                .withClaim("authorities", authorities)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512("BlueTeam".getBytes()));
    }
}
