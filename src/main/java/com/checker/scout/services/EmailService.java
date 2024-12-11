package com.checker.scout.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.checker.scout.controllers.home.interfaces.HomeInt;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void sendEmail(HomeInt.webSiteMessage webSiteMessage, String email) {

         executor.submit(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("software@spaceagency.pro");

                if (webSiteMessage.getSubject()==null ||"".equals(webSiteMessage.getSubject())) {
                    helper.setSubject(webSiteMessage.getWebSiteNome());
                }else{
                    helper.setSubject(webSiteMessage.getSubject());
                }

                helper.setTo(email);
                helper.setText("<p>" + webSiteMessage.getMessage() + "</p>", true);
                mailSender.send(message);

            } catch (MessagingException e) {
                System.err.println(e.getMessage());
            }
        });

    }
}
