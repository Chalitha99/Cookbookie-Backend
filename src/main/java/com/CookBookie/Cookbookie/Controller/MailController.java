package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.DTO.MailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailsDTO mailsDTO) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("chalithaciscoitn@gmail.com");
            message.setTo("chalithaciscoitn@gmail.com");
            message.setText(mailsDTO.getMessage());

            mailSender.send(message);
            return ResponseEntity.ok("Mail Sent Successfully");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
