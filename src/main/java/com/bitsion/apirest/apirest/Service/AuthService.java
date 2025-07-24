package com.bitsion.apirest.apirest.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitsion.apirest.apirest.Entities.User;
import com.bitsion.apirest.apirest.Repositories.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        return generateToken(username);
    }

    public String register(String username, String password) {
         if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return generateToken(username);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, "123456")
                .compact();
    }
}
