package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @Autowired
    private static JavaMailSender javaMailSender;

    public static void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("vovantin9502@gmail.com");
        message.setSubject("Test Email");
        message.setText("This is a test email");

        javaMailSender.send(message);
    }

    public static void main(String[] args) {
        sendEmail();
    }
    @GetMapping("")
    public String send(){
        sendEmail();
        return "list";
    }
}
