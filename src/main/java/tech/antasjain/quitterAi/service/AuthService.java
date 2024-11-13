package tech.antasjain.quitterAi.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.configs.JwtTokenUtil;
import tech.antasjain.quitterAi.configs.UserDetailsImpl;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String registerUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return jwtTokenUtil.generateToken(new UserDetailsImpl(user));
        }
        else{
            throw new RuntimeException("Invalid Credentials");
        }
    }
}
