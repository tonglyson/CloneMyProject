package com.example.clonemyproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class InputInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "{name.notEmpty}")
    @Min(value = 1,message = "{name.notEmpty}")
    private int quantity;
    @Min(value = 1,message = "{name.notEmpty}")
    private int entryPrice;
    @Min(value = 1,message = "{name.notEmpty}")
    private int salePrice;
    private LocalDate created = LocalDate.now();
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Khong duoc de trong product id")
    private Product product;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(nullable = false)
    private Input input;
    @OneToMany(mappedBy = "inputInfo")
    @JsonIgnore
    private List<OutputInfo> outputInfos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(int entryPrice) {
        this.entryPrice = entryPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public List<OutputInfo> getOutputInfos() {
        return outputInfos;
    }

    public void setOutputInfos(List<OutputInfo> outputInfos) {
        this.outputInfos = outputInfos;
    }

    public InputInfo() {
    }
}
