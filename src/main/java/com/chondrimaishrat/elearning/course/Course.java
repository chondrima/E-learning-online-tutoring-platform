package com.chondrimaishrat.elearning.course;

import com.chondrimaishrat.elearning.user.UserAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 60)
    private String category;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CourseStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    private UserAccount instructor;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Course() {
    }

    public Course(
            String title,
            String category,
            String description,
            BigDecimal price,
            UserAccount instructor
    ) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.instructor = instructor;
        this.status = CourseStatus.DRAFT;
    }

    @PrePersist
    private void setCreatedAt() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public CourseStatus getStatus() { return status; }
    public UserAccount getInstructor() { return instructor; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void publish() {
        this.status = CourseStatus.PUBLISHED;
    }
}
