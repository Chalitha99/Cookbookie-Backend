package com.CookBookie.Cookbookie.Controller;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;



    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok("User added Successfully");
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepo.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
