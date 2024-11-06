package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.Service.FileUploadUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/api/img")
public class ImageUploadController {
    FileUploadUtil fileUploadUtil = new FileUploadUtil();
    @PostMapping
    public void saveImage(@RequestParam("files")MultipartFile[] files) {
        String uploadDir = "profileImagesFolder";
        Arrays.asList(files).stream().forEach(file -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            System.out.println(fileName);

            try{
                fileUploadUtil.saveFile(uploadDir, fileName, file);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
