package com.example.kitchensink.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "members")
public class Member implements Serializable {

    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters")
    private String name;

    @NotNull
    @NotEmpty
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull
    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
    @Pattern(regexp = "^\\d+$", message = "Phone number must contain only digits")
    private String phoneNumber;

    public Member(String name, String mail) {
        this.setName(name);
        this.setEmail(mail);
    }

    public Member() {
    }

    // Getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}