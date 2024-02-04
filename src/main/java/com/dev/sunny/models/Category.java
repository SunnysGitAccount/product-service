package com.dev.sunny.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    @Column(length = 500)
    private String description;
//    Describe the relationship between Category and Product
//    One category can have many products
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
