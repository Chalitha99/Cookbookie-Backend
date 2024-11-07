//package com.CookBookie.Cookbookie.Controller;
//
//import com.CookBookie.Cookbookie.Service.FileUploadUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/api/img")
//@RequiredArgsConstructor
//public class ImageUploadController {
//    private FileUploadUtil fileUploadUtil ;
//
////    @PostMapping
////    public void saveImage(@RequestParam("files")MultipartFile[] files) {
////        String uploadDir = "profileImagesFolder";
////        Arrays.asList(files).stream().forEach(file -> {
////            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
////            System.out.println(fileName);
////
////            try{
////                fileUploadUtil.saveFile(uploadDir, fileName, file);
////            }catch (Exception e){
////                e.printStackTrace();
////            }
////        });
////    }
//
//
//
//
//    @PostMapping
//    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file,
//                                                     @RequestParam("name") String name) throws IOException {
//        String uploadImage = fileUploadUtil.uploadImageToFileSystem(file, name);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//}
