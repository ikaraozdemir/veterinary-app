package com.patika.cohort3.veterinaryapp.exception;

public class AppointmentExistsException extends RuntimeException {
    public AppointmentExistsException(String message) {
        super(message);
    }
}
