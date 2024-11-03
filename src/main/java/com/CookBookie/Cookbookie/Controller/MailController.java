package com.CookBookie.Cookbookie.Controller;

import com.CookBookie.Cookbookie.DTO.MailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String sendMail(@RequestBody MailsDTO mailsDTO) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(mailsDTO.getSubject());
            message.setFrom("chalithaciscoitn@gmail.com");
            message.setTo(mailsDTO.getToMail());
            message.setText(mailsDTO.getBody());

            mailSender.send(message);
            return "Mail Sent Successfully";
        }
        catch (Exception e) {
            return e.getMessage();
        }


    }
}
