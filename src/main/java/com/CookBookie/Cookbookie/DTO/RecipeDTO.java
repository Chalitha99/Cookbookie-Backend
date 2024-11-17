package com.CookBookie.Cookbookie.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

    private String id;

    private String foodName;

    private String ingredients;

    private String description;

    private String category;

    private byte[] imgPath;



}
