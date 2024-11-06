package com.CookBookie.Cookbookie.Service;

import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter.CURRENT_USER;

public class FileUploadUtil {
    UserRepo userRepo;
    public void saveFile(String uploadDir, String filename, MultipartFile multipartFile) throws IOException {

        Path uploadPath= Paths.get("E:\\CookBookie\\Cookbookie-Backend\\src\\main\\resources\\static\\" + uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);

        }
        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            String user = CURRENT_USER;
            User user1 = userRepo.findByUsername(user).orElseThrow();
            user1.setImgPath(filePath.toString());
            userRepo.save(user1);
        }
        catch(IOException ioException){

        }



    }
}
