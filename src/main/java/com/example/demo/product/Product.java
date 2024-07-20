package com.example.demo.product;

import jakarta.persistence.*;

/**
 * Example class to demonstrate method-level security (PostAuthorize annotation)
 */
@Entity
@Table(name = "products", schema = "foo")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "price")
    private Float price;


    public Product(String name, String owner, Float price) {
        this.name = name;
        this.owner = owner;
        this.price = price;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
