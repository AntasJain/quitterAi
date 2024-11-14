package tech.antasjain.quitterAi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.configs.JwtTokenUtil;
import tech.antasjain.quitterAi.dto.AuthResponse;
import tech.antasjain.quitterAi.entity.User;

import java.util.ArrayList;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(String email, String password, String role) {
        // Delegate user registration to UserService
        User newUser = userService.createUser(email, password, role);

        // Generate token
        String token = jwtTokenUtil.generateToken(new org.springframework.security.core.userdetails.User(
                newUser.getEmail(), newUser.getPassword(), new ArrayList<>()
        ));

        return new AuthResponse(token, newUser.getId(), newUser.getEmail(), newUser.getRole());
    }

    public AuthResponse login(String email, String password) {
        // Use AuthenticationManager to validate credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // On successful authentication, retrieve user details
        User user = userService.findByEmail(email);

        // Generate token
        String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
    }
}
