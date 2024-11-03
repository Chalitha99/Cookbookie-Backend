package com.CookBookie.Cookbookie.Filter;

public class AuthenticationResponse {


        private String token;

        private String message;

        public AuthenticationResponse(String token, String message) {
            this.message = message;
            this.token = token;
        }


        public String getToken() {
            return token;
        }

        public String getMessage() {
            return message;
        }
    }

