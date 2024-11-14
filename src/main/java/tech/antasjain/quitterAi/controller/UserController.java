package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MutationMapping
    public String registerUser(@Argument String email, @Argument String password, @Argument String role) {
        return userService.registerUser(email, password, role);
    }

    @MutationMapping
    public String loginUser(@Argument String email, @Argument String password) {
        return userService.loginUser(email, password);
    }

    @QueryMapping
    public Optional<User> getUserProgress(@Argument Long id) {
        return userService.getUserProgress(id);
    }
}
