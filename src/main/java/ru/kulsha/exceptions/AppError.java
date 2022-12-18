package ru.kulsha.exceptions;

public class AppError {
    private int StatusCode;
    private String message;

    public AppError(int statusCode, String message) {
        StatusCode = statusCode;
        this.message = message;
    }

    public AppError() {
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
