package com.app.ecommerce.utils;

import com.app.ecommerce.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender {
    @Value("${spring.mail.username}")
    private static String mail;

    public static void sendEmailMessage(final User user, JavaMailSender javaMailSender) throws org.springframework.mail.MailException{
        final String recipientAddress = user.getEmail();
        final String subject = "Information de connexion";

        final String message ="Vous avez été correctement enregistré. Reçevez vos information de connexion ci dessous:.";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \n" + "Username:  "+user.getEmail()+ "\n" +"Password:"+user.getPassword());
        email.setFrom(mail);
        javaMailSender.send(email);
    }
}
