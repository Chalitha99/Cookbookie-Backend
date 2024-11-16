package com.CookBookie.Cookbookie.Service;

import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter.CURRENT_USER;
@Service
@RequiredArgsConstructor
public class FileUploadUtil {
    private final UserRepo userRepo;

    public void saveFile(String uploadDir, String filename, MultipartFile multipartFile ,String userID) throws IOException {

        Path uploadPath = Paths.get("E:\\CookBookie\\Cookbookie-Backend\\src\\main\\resources\\static\\" + uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);

        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            updateUserProfileImagePath(userID, filePath.toString());

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new IOException("Could not save file: " + filename, ioException);
        }
    }
    private void updateUserProfileImagePath(String userID, String filePath) {
        userRepo.findById(userID).ifPresent(user -> {
            user.setImgPath(filePath);  // Assuming profileImagePath is the field in User entity
            userRepo.save(user);  // Save the updated user with the new profile image path
        });
    }

}