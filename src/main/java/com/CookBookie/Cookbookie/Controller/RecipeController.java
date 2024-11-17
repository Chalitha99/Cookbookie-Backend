package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter;
import com.CookBookie.Cookbookie.Model.Recipes;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.RecipeRepo;
import com.CookBookie.Cookbookie.Service.FileUploadUtil;
import com.CookBookie.Cookbookie.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/recipe")
public class RecipeController {


    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;

//    @PostMapping("/addRecipe")
//    public ResponseEntity<?> addRecipe(@RequestBody Recipes recipes ,
//                                       @RequestParam("recipeImg") MultipartFile recipeImg)
//    {
//        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
//        User user = userService.getUserAsModel(currentUser);
//
//        String uploadDir = "RecipeImageFolder";
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(recipeImg.getOriginalFilename()));
//
//        try {
//            String filePath = fileUploadUtil.saveRecipeImg(uploadDir, fileName, recipeImg);
//            recipes.setImgPath(filePath);
//            recipes.setUser(user);
//            recipeRepo.save(recipes);
//            return ResponseEntity.ok("Recipe added successfully");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to upload recipe image and details.");
//        }
//
//    }

    @PostMapping(value = "/addRecipe")
    public ResponseEntity<?> addRecipe(
            @RequestParam("recipeImg") MultipartFile recipeImg,
            @RequestParam("foodName") String foodName,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("category") String category,
            @RequestParam("description") String description) {
        try {
            // Extract the current user
            String currentUser = JwtAuthenticationFilter.CURRENT_USER;
            User user = userService.getUserAsModel(currentUser);

            // Handle file upload
            String uploadDir = "RecipeImageFolder";
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(recipeImg.getOriginalFilename()));
            String filePath = fileUploadUtil.saveRecipeImg(uploadDir, fileName, recipeImg);

            // Create and save the recipe
            Recipes recipe = new Recipes();
            recipe.setFoodName(foodName);
            recipe.setIngredients(ingredients);
            recipe.setCategory(category);
            recipe.setDescription(description);
            recipe.setImgPath(filePath);
            recipe.setUser(user);

            recipeRepo.save(recipe);

            return ResponseEntity.ok("Recipe added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload recipe image and details.");
        }
    }



}
