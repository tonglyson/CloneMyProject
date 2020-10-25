package com.example.clonemyproject.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "{name.notEmpty}")
    @Column(nullable = false)
    private String name;
    @NotEmpty(message = "{name.notEmpty}")
    @Email(message = "{email.isEmail}")
    @Column(unique = true,nullable = false)
    private String email;
    @NotEmpty(message = "{name.notEmpty}")
    @Column(nullable = false)
    private String address;
    @Pattern(regexp = "[0-9]{5,11}",message = "Nhập số điện thoại từ 5-11 chữ số")
    @Column(nullable = false)
    private String phone;
    private LocalDateTime created = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Supplier() {
    }
}
