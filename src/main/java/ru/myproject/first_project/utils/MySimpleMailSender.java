package ru.myproject.first_project.utils;

public interface MySimpleMailSender {
    void send(String to, String subject, String body);
}
