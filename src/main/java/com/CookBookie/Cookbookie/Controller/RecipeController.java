package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.DTO.RecipeDTO;
import com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter;
import com.CookBookie.Cookbookie.Model.Recipes;
import com.CookBookie.Cookbookie.Model.Reviews;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.RecipeRepo;
import com.CookBookie.Cookbookie.Service.FileUploadUtil;
import com.CookBookie.Cookbookie.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {


    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private UserService userService;


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

    @GetMapping("/myRecipes")
    public List<RecipeDTO> getMyRecipes() throws IOException {

        List<RecipeDTO>  recipeDTOList = new ArrayList<>();
        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
        User user = userService.getUserAsModel(currentUser);
        String userId = user.getId();

        ObjectId userObjectId = new ObjectId(userId);
        List<Recipes> recipeList= recipeRepo.findByUser(userObjectId);

        for (Recipes recipe : recipeList) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setFoodName(recipe.getFoodName());
            recipeDTO.setIngredients(recipe.getIngredients());
            recipeDTO.setCategory(recipe.getCategory());
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTO.setId(recipe.getId());
            recipeDTO.setImgPath(fileUploadUtil.getImageData(recipe.getImgPath()));

            recipeDTOList.add(recipeDTO);
        }
    return recipeDTOList;

    }

    @GetMapping("/allRecipes")
    public List<RecipeDTO> getAllRecipes() throws IOException {

        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        List<Recipes> recipeList= recipeRepo.findAll();

        for (Recipes recipe : recipeList) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setFoodName(recipe.getFoodName());
            recipeDTO.setIngredients(recipe.getIngredients());
            recipeDTO.setCategory(recipe.getCategory());
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTO.setId(recipe.getId());
            recipeDTO.setImgPath(fileUploadUtil.getImageData(recipe.getImgPath()));

            recipeDTOList.add(recipeDTO);
        }
        return recipeDTOList;

    }

}
