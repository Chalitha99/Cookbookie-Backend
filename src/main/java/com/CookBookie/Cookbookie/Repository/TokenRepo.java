package com.CookBookie.Cookbookie.Repository;

import com.CookBookie.Cookbookie.Model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends MongoRepository<Token, String> {

    Optional<Token> findByToken(String token);
    List<Token> findByUserId(String userId);
}
