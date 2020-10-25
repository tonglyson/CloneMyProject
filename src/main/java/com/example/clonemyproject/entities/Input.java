package com.example.clonemyproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class
Input {
    @Id
    @NotEmpty(message = "{name.notEmpty}")
    @Column(unique = true,nullable = false)
    private String id;
    @NotEmpty(message = "{name.notEmpty}")
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    @NotEmpty(message = "{name.notEmpty}")
    private String user_input;
    private LocalDateTime created=LocalDateTime.now();
    @OneToMany(mappedBy = "input",cascade = CascadeType.ALL)
    @Valid
    @JsonManagedReference
    private List<InputInfo> inputs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<InputInfo> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputInfo> inputs) {
        this.inputs = inputs;
    }

    public Input() {
    }
}
