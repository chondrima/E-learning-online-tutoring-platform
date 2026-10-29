package com.chondrimaishrat.elearning.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationForm {

    @NotBlank(message = "Full name is required.")
    @Size(max = 100, message = "Full name must be 100 characters or fewer.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    @Size(max = 160, message = "Email must be 160 characters or fewer.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(
            min = 8,
            max = 72,
            message = "Password must contain 8 to 72 characters."
    )
    private String password;

    @NotBlank(message = "Choose an account type.")
    private String role;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}