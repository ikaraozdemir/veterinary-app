package com.patika.cohort3.veterinaryapp.exception;

import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<Result> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(ResultHelper.alreadyExist(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Result> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return new ResponseEntity<>(ResultHelper.validateError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Result> handleGeneralDateTimeParseException(DateTimeParseException e) {
        return new ResponseEntity<>(ResultHelper.dateFormatError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateValidationException.class)
    public ResponseEntity<Result> handleProtectionEndDateNotArrivedException(DateValidationException e) {
        return new ResponseEntity<>(ResultHelper.validateError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Result> handleDoctorNotAvailableException(DoctorNotAvailableException e) {
        return new ResponseEntity<>(ResultHelper.notAvailableError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentExistsException.class)
    public ResponseEntity<Result> handleAppointmentExistsException(AppointmentExistsException e) {
        return new ResponseEntity<>(ResultHelper.cannotDeleteError(e.getMessage()), HttpStatus.CONFLICT);
    }

}


