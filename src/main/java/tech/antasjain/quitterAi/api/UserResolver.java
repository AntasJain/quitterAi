package tech.antasjain.quitterAi.api;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.UserRepository;
import tech.antasjain.quitterAi.service.AuthService;

import java.util.Optional;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final AuthService authService;
    private final UserRepository userRepository;

    public UserResolver(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public User getUserProgress(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new GraphQLException("User not found with ID: " + id));
    }

    public String registerUser(String email, String password, String role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return authService.registerUser(user);
    }

    public String loginUser(String email, String password) {
        return authService.loginUser(email, password);
    }
}
