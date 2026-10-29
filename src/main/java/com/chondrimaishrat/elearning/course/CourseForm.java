package com.chondrimaishrat.elearning.course;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CourseForm {
    @NotBlank(message = "Course title is required.")
    @Size(max = 150, message = "Course title must be 150 characters or fewer.")
    private String title;

    @NotBlank(message = "Category is required.")
    @Size(max = 60, message = "Category must be 60 characters or fewer.")
    private String category;

    @NotBlank(message = "Course description is required.")
    @Size(max = 2000, message = "Description must be 2000 characters or fewer.")
    private String description;

    @NotNull(message = "Enter 0 for a free course, or enter a price.")
    @DecimalMin(value = "0.00", message = "Price cannot be negative.")
    private BigDecimal price;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
