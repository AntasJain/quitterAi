package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.dto.AuthResponse;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.AuthService;
import tech.antasjain.quitterAi.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @MutationMapping
    public AuthResponse registerUser(@Argument String email, @Argument String password, @Argument String role) {
        return authService.register(email, password, role);
    }

    @MutationMapping
    public AuthResponse loginUser(@Argument String email, @Argument String password) {
        return authService.login(email, password);
    }
//
//    @QueryMapping
//    public Optional<User> getUserProgress(@Argument Long id) {
//        return authService.getUserProgress(id);
//    }
}
