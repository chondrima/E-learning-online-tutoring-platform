package com.chondrimaishrat.elearning.enrollment;

import com.chondrimaishrat.elearning.course.Course;
import com.chondrimaishrat.elearning.user.UserAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(
        name = "uk_enrollment_student_course",
        columnNames = {"student_id", "course_id"}
))
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private UserAccount student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "progress_percent", nullable = false)
    private int progressPercent;

    @Column(name = "enrolled_at", nullable = false, updatable = false)
    private LocalDateTime enrolledAt;

    protected Enrollment() { }

    public Enrollment(UserAccount student, Course course) {
        this.student = student;
        this.course = course;
        this.progressPercent = 0;
    }

    @PrePersist
    private void setEnrolledAt() {
        if (enrolledAt == null) {
            enrolledAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public UserAccount getStudent() { return student; }
    public Course getCourse() { return course; }
    public int getProgressPercent() { return progressPercent; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
}
