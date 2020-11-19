package ru.myproject.first_project.exceptions;

public class InvalidVerificationTokenException extends Exception {

    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
