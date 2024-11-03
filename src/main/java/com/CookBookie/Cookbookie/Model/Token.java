package com.CookBookie.Cookbookie.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    private String id;
    private String token;
    private boolean loggedOut;

    @DocumentReference(collection = "User")
    private User user;
}
