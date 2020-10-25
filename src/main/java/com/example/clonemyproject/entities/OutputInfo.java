package com.example.clonemyproject.entities;


import com.example.clonemyproject.customanotation.InsertOnlyValidations;
import com.example.clonemyproject.customanotation.QuantityValidator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@QuantityValidator
public class OutputInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 1,message = "{name.notEmpty}")
    @Column(nullable = false)
    private int quantity;
    private LocalDate created = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY,optional = false)

    private Product productOutput;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)

    private InputInfo inputInfo;
    public OutputInfo() {
    }

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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Product getProductOutput() {
        return productOutput;
    }

    public void setProductOutput(Product productOutput) {
        this.productOutput = productOutput;
    }

    public InputInfo getInputInfo() {
        return inputInfo;
    }

    public void setInputInfo(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
    }
}
