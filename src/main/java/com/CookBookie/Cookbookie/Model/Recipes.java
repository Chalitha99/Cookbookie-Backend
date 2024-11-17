package com.CookBookie.Cookbookie.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipes {

    @Id
    private String id;
    private String foodName;
    private String ingredients;
    private String description;
    private String category;
    private String imgPath;

    @DocumentReference(collection = "User")
    private User user;
}
