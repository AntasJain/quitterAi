package tech.antasjain.quitterAi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.antasjain.quitterAi.configs.JwtTokenUtil;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.UserRepository;
import tech.antasjain.quitterAi.service.AuthService;

import java.util.Optional;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthServiceTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthService authService;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        String result = authService.registerUser(user);
        assertEquals("User registered successfully!", result);
    }

    @Test
    public void testLoginUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(encodedPassword);

        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        Mockito.when(jwtTokenUtil.generateToken(Mockito.any())).thenReturn("mock-jwt-token");

        String token = authService.loginUser("test@example.com", "password");
        assertNotNull(token);
    }
}
