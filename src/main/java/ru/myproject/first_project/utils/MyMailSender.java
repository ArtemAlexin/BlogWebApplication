package ru.myproject.first_project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender implements MySimpleMailSender {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.properties.from}")
    private String fromAddress;
    @Value("${spring.mail.properties.fromEmail}")
    private String fromEmail;
    @Autowired
    public MyMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void send(String to, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromAddress + " <" + fromEmail + ">");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        mailSender.send(mail);
    }
}
