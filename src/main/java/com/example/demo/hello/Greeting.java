package com.example.demo.hello;

/**
 * Example class to demonstrate method-level security (PostAuthorize annotation)
 */
public class Greeting {

    private String message;
    private boolean isPositive;

    public Greeting(String message, boolean isPositive) {
        this.message = message;
        this.isPositive = isPositive;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public String getMessage() {
        return message;
    }
}
