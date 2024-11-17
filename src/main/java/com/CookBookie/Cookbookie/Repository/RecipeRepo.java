package com.CookBookie.Cookbookie.Repository;

import com.CookBookie.Cookbookie.Model.Recipes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepo extends MongoRepository <Recipes,String> {

}
