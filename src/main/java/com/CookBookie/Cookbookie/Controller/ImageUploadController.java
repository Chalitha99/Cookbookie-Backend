package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.Service.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


@RestController
@RequestMapping("/api/img")
@RequiredArgsConstructor
public class ImageUploadController {
    private final FileUploadUtil fileUploadUtil;

    @PutMapping("/uploadProfilePicture/{userID}")
    public ResponseEntity<?> saveProfilePicture(
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @PathVariable String userID) {
        String uploadDir = "profileImagesFolder";
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(profilePicture.getOriginalFilename()));

        try {
            fileUploadUtil.saveFile(uploadDir, fileName, profilePicture,userID);
            // Optionally update the user's profile picture path in the database here.
            return ResponseEntity.ok("Profile picture updated successfully for user ID: " + userID);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload profile picture. Please try again.");
        }
    }
}

