package com.CookBookie.Cookbookie.Controller;
import com.CookBookie.Cookbookie.Filter.AuthenticationResponse;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class AuthenticationController {

        private final AuthenticationService authService;

        public AuthenticationController(AuthenticationService authService) {
            this.authService = authService;
        }


        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody User request
        ) {
            return ResponseEntity.ok(authService.register(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> login(
                @RequestBody User request
        ) {
            return ResponseEntity.ok(authService.authenticate(request));
        }



    }

