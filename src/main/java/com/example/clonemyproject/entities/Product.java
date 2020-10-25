package com.example.clonemyproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Product {
    @Id
    @NotEmpty(message = "{name.notEmpty}")
    @Column(unique = true,nullable = false)
    private String id;
    @NotEmpty(message = "{name.notEmpty}")
    @Column(nullable=false)
    private String name;
    private boolean status=false;
    @ManyToOne
    @JsonBackReference
    private Unit unit;
    @OneToMany(mappedBy = "productOutput")
    @JsonIgnore
    private List<OutputInfo> outputInfos = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<InputInfo> inputInfos = new ArrayList<>();

    public List<InputInfo> getInputInfos() {
        return inputInfos;
    }

    public void setInputInfos(List<InputInfo> inputInfos) {
        this.inputInfos = inputInfos;
    }

    public List<OutputInfo> getOutputInfos() {
        return outputInfos;
    }

    public void setOutputInfos(List<OutputInfo> outputInfos) {
        this.outputInfos = outputInfos;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Product() {
    }
}
