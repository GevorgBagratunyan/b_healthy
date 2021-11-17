package com.blueteam.appointment.security.properties;


public class JwtConfig {
    public static final String SECRET = "BlueTeam";
//    public static final int EXPIRATION_TIME = 10*24*60*60*1000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
