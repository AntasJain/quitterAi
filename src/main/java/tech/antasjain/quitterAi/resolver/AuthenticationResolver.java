package tech.antasjain.quitterAi.resolver;


import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.antasjain.quitterAi.configs.JwtTokenUtil;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.UserService;

@Component
public class AuthenticationResolver {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthenticationResolver(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    public DataFetcher<User> me() {
        return dataFetchingEnvironment -> {
            String token = (String) dataFetchingEnvironment.getContext(); // Assume the token is passed in the context

            // Extract username from the token
            String username = jwtTokenUtil.extractUsername(token);

            // Fetch user details from the database using the service method
            User user = userService.findByEmail(username);

            if (user == null) {
                throw new RuntimeException("User not found!");
            }

            return user;
        };
    }
}
