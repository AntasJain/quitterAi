package tech.antasjain.quitterAi.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String registerUser(String email, String password, String role) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return "Email already in use";
        }
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword); // Consider hashing passwords before saving
        newUser.setRole(role);

        userRepository.save(newUser);
        return "User registered successfully";
    }

    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return "Login successful";
            } else {
                return "Invalid email or password";
            }
        } else {
            return "Invalid email or password";
        }
    }

    public Optional<User> getUserProgress(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), new ArrayList<>()); // empty authorities list
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

}
