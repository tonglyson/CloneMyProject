package com.example.clonemyproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Unit {
    @Id
    @NotEmpty(message = "{name.notEmpty}")
    @Column(unique = true)
    private String id;
    @NotEmpty(message = "{name.notEmpty}")
    @Column(nullable = false)
    private String name;
    private LocalDateTime created = LocalDateTime.now();
    @OneToMany(mappedBy = "unit",cascade = CascadeType.ALL)
    @JsonManagedReference
    @Valid
    private Set<Product> products = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Unit() {
    }
}
