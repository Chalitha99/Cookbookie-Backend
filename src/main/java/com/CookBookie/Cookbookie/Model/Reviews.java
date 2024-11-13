package com.CookBookie.Cookbookie.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {


        @Id
        private String id;
        private int rating;
        private String comment;

        @DocumentReference(collection = "User")
        private User user;
}

