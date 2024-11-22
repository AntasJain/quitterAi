package tech.antasjain.quitterAi.controller;


import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.dto.AuthResponse;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.AuthService;
import tech.antasjain.quitterAi.service.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final  UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @MutationMapping
    public AuthResponse registerUser(@Argument String email, @Argument String password, @Argument String role) {
        try {
            AuthResponse response = authService.register(email, password, role);

            logger.info("AuthResponse: {}", response); // Use logger instead of System.out.println
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to logs for troubleshooting
            throw new RuntimeException("Failed to register user", e); // Wrap in runtime exception
        }
    }


    @MutationMapping
    public AuthResponse loginUser(@Argument String email, @Argument String password) {
        return authService.login(email, password);
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(BadCredentialsException e){
        logger.error("Invalid login attempt", e);
        return GraphqlErrorBuilder.newError()
                .message("Invalid email or password")
                .errorType(ErrorType.ValidationError)
                .build();
    }

    @QueryMapping
    public User me(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }
        User user = userService.findByEmail(auth.getName());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return user;
    }


}
