//package com.CookBookie.Cookbookie.Service;
//
//import com.CookBookie.Cookbookie.Model.User;
//import com.CookBookie.Cookbookie.Repository.UserRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//import static com.CookBookie.Cookbookie.Filter.JwtAuthenticationFilter.CURRENT_USER;
//@Service
//@RequiredArgsConstructor
//public class FileUploadUtil {
//    private final UserRepo userRepo;
//
//    private final String uploadPath= "E:\\CookBookie\\Cookbookie-Backend\\src\\main\\resources\\static\\profileImagesFolder" ;
//
////    public void saveFile(String uploadDir, String filename, MultipartFile multipartFile) throws IOException {
////
////        Path uploadPath= Paths.get("E:\\CookBookie\\Cookbookie-Backend\\src\\main\\resources\\static\\" + uploadDir);
////        if (!Files.exists(uploadPath)) {
////            Files.createDirectories(uploadPath);
////
////        }
////        try(InputStream inputStream = multipartFile.getInputStream()) {
////            Path filePath = uploadPath.resolve(filename);
////            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
////            String user = CURRENT_USER;
////            User user1 = userRepo.findByUsername(user).orElseThrow();
////            System.out.println(filePath);
////            user1.setImgPath(filePath.toString());
////            userRepo.save(user1);
////        }
////        catch(IOException ioException){
////
////        }
////    }
//
//
//    public String uploadImageToFileSystem(MultipartFile file, String name) throws IOException {
//        String filePath=uploadPath+file.getOriginalFilename();
//
////        User fileData=userRepo.save(User.builder()
////                .name(name)
////                .imgPath(filePath).build());
//
//        file.transferTo(new File(filePath));
//
////        if (fileData != null) {
////            return "file uploaded successfully : " + filePath;
////        }
//        return "file uploaded successfully";
//    }
//}
