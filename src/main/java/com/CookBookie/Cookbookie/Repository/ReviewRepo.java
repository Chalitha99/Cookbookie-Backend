package com.CookBookie.Cookbookie.Repository;

import com.CookBookie.Cookbookie.Model.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepo extends MongoRepository<Reviews, String> {

}
