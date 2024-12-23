package com.school.student_mg.exception;

public class NotFoundException extends RuntimeException{
    private boolean status;
    private String message;

    public NotFoundException(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public NotFoundException() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
