package com.CookBookie.Cookbookie.Config;

import com.CookBookie.Cookbookie.Model.Token;
import com.CookBookie.Cookbookie.Repository.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepo tokenRepo;

    public CustomLogoutHandler(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {

            return;
        }


        String token = authHeader.substring(7);
        Token storedToken = tokenRepo.findByToken(token).orElse(null);

        if(storedToken != null) {
            storedToken.setLoggedOut(true);
            tokenRepo.save(storedToken);
        }
    }
}
