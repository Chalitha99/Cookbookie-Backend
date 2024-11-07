package com.CookBookie.Cookbookie.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

        private String id;

        private String name;

        private String username;

        private String phone;

        private String currentPassword;

        private String newPassword;

        private String role;

        private String imgPath;
}
