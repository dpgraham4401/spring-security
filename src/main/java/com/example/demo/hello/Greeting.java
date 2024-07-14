package com.example.demo.hello;

import jakarta.persistence.*;

/**
 * Example class to demonstrate method-level security (PostAuthorize annotation)
 */
@Entity
@Table(name = "greetings", schema = "foo")
public class Greeting {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "is_positive")
    private boolean isPositive;

    @Column(name = "user_id")
    private String userId;

    public Greeting(String message, boolean isPositive) {
        this.message = message;
        this.isPositive = isPositive;
    }

    public Greeting() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
}
