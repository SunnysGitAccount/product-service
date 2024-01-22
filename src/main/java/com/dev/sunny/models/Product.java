package com.dev.sunny.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private Double price;
//    Describes the relationship between Product and Category
//    Many products can belong to one category (many-to-one)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;
    private String description;
    private String image;
}