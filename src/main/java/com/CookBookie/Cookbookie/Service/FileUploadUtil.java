package com.CookBookie.Cookbookie.Service;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    public byte[] getImageData(String filePath) throws IOException{
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        try (FileInputStream inputStream = new FileInputStream(file);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();
        }
    }


    public String saveRecipeImg(String uploadDir, String filename, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("E:\\CookBookie\\Cookbookie-Backend\\src\\main\\resources\\static\\" + uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);

        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new IOException("Could not save Recipe image: " + filename, ioException);
        }
    }

}