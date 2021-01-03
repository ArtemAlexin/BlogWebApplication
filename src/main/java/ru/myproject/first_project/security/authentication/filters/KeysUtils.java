package ru.myproject.first_project.security.authentication.filters;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class KeysUtils {
    private static String signingKey;

    private KeysUtils() {}
    public static SecretKey generateKey() {
        return Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
    }
    @Value("${jwt.signing.key}")
    public void setSigningKey(String name) {
        signingKey = name;
    }
}
