package com.CookBookie.Cookbookie.Repository;

import com.CookBookie.Cookbookie.Model.Recipes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepo extends MongoRepository <Recipes,String> {
    @Query("{ 'user': ?0 }")
    List<Recipes> findByUser(ObjectId userId);
}
