package com.CookBookie.Cookbookie.Controller;
import com.CookBookie.Cookbookie.DTO.UserDTO;
import com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter;
import com.CookBookie.Cookbookie.Model.Reviews;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.ReviewRepo;
import com.CookBookie.Cookbookie.Repository.UserRepo;

import com.CookBookie.Cookbookie.Service.FileUploadUtil;
import com.CookBookie.Cookbookie.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok("User added Successfully");
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @GetMapping("/getUser")
    public UserDTO getUser() {
        String currentUser = JwtAuthenticationFilter.CURRENT_USER; // Retrieve the current user from JWT
        return userService.getUserByUsername(currentUser);
    }

    @PutMapping("/updateUser/{userID}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable String userID) {
        userService.updateUser(userDTO, userID);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(@RequestBody Reviews reviews) {
        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
        User user = userService.getUserAsModel(currentUser);
        reviews.setUser(user);
        reviewRepo.save(reviews);
        return ResponseEntity.ok("Reviews added Successfully");
    }

    @GetMapping("/reviewList")
    public List<Map<String, Object>> getAllReviews() throws IOException {
        List<Reviews> reviewList = reviewRepo.findAll();
        List<Map<String, Object>> reviewsWithUserImage = new ArrayList<>();

        for (Reviews review : reviewList) {

            User user = review.getUser();

            Map<String, Object> reviewWithUser = new HashMap<>();
            reviewWithUser.put("review", review);
            reviewWithUser.put("userImage", fileUploadUtil.getImageData(user.getImgPath()));  // Assuming imgPath is a byte array
            reviewWithUser.put("name",user.getName());
            reviewsWithUserImage.add(reviewWithUser);

        }

        return reviewsWithUserImage;
    }


}