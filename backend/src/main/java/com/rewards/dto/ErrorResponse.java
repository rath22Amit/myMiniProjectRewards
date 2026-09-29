package com.rewards.dto;

import java.util.List;

public class ErrorResponse {

    public int status;

    public String message;

    public List<String> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}