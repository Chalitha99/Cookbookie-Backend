package com.CookBookie.Cookbookie.Service;
import com.CookBookie.Cookbookie.Filter.AuthenticationResponse;
import com.CookBookie.Cookbookie.Model.Token;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.TokenRepo;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthenticationService {




        private final UserRepo userRepo;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;



        private final AuthenticationManager authenticationManager;
        private final TokenRepo tokenRepo;
        public AuthenticationService(UserRepo userRepo,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     TokenRepo tokenRepo,
                                     AuthenticationManager authenticationManager) {
            this.userRepo = userRepo;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
            this.tokenRepo = tokenRepo;
            this.authenticationManager = authenticationManager;
        }

        public AuthenticationResponse register(User request) {

            // check if user already exist. if exist than authenticate the user
            if(userRepo.findByUsername(request.getPassword()).isPresent()) {
                return new AuthenticationResponse(null,"User already exist");
            }

            User user = new User();
            user.setName(request.getName());
            user.setUsername(request.getUsername());
            user.setPhone(request.getPhone());
            user.setRole(request.getRole());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user = userRepo.save(user);

            return new AuthenticationResponse(null,"User registration was successful");

        }

    private void saveUserToken(String token, User user) {
        Token token1 = new Token();
        token1.setToken(token);
        token1.setLoggedOut(false);
        token1.setUser(user);
        tokenRepo.save(token1);
    }

    public AuthenticationResponse authenticate(User request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = userRepo.findByUsername(request.getUsername()).orElseThrow();
            List<Token> tokens = tokenRepo.findByUserId(user.getId());

            for (Token token : tokens) {
            tokenRepo.deleteById(token.getId());
        }
            String token = jwtService.generateToken(user);
            saveUserToken(token,user);

            return new AuthenticationResponse(token,"User login was successful");

        }}



