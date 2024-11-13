package com.CookBookie.Cookbookie.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailsDTO {

    private String toMail;
    private String subject;
    private String message;

}
