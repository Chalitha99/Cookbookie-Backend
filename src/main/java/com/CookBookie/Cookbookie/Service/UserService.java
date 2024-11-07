package com.CookBookie.Cookbookie.Service;

import com.CookBookie.Cookbookie.DTO.UserDTO;
import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapperConfig;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserDTO getUserByUsername(String username) {
        User selectedUser = userRepo.findByUsername(username).orElse(null);
        return modelMapperConfig.map(selectedUser, UserDTO.class);

    }


    public UserDTO updateUser(UserDTO userDTO, String userID) {
//        User user = userRepo.findById(userID).orElse(null);
//        user.setName(userDTO.getName());
//        user.setUsername(userDTO.getUsername());
//        user.setPhone(userDTO.getPhone());
//        user.setPassword(userDTO.getPassword());
//        User updatedUser= userRepo.save(user);
//        return modelMapperConfig.map(updatedUser, UserDTO.class);
        User user = userRepo.findById(userID).orElse(null);
            if (user == null) {
                throw new RuntimeException("User not found");
            }

            // Check if current password matches
            if (userDTO.getCurrentPassword() != null && passwordEncoder.matches(userDTO.getCurrentPassword(), user.getPassword())) {
                // Update to the new password if the match is successful
                user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
            } else if (userDTO.getCurrentPassword() != null) {
                throw new RuntimeException("Current password is incorrect");
            }

            // Update other fields
            user.setName(userDTO.getName());
            user.setUsername(userDTO.getUsername());
            user.setPhone(userDTO.getPhone());

            User updatedUser = userRepo.save(user);
            return modelMapperConfig.map(updatedUser, UserDTO.class);
        }
}



