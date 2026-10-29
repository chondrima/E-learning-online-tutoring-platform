package com.chondrimaishrat.elearning.review;
import com.chondrimaishrat.elearning.course.Course;
import com.chondrimaishrat.elearning.user.UserAccount;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="course_reviews",uniqueConstraints=@UniqueConstraint(columnNames={"student_id","course_id"})) public class Review { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @ManyToOne(optional=false) @JoinColumn(name="student_id") private UserAccount student; @ManyToOne(optional=false) @JoinColumn(name="course_id") private Course course; @Column(nullable=false) private int stars; @Column(length=1000) private String comment; @Column(nullable=false) private LocalDateTime createdAt; protected Review(){} public Review(UserAccount s,Course c,int stars,String comment){student=s;course=c;this.stars=stars;this.comment=comment;createdAt=LocalDateTime.now();} public int getStars(){return stars;} public String getComment(){return comment;} public UserAccount getStudent(){return student;} }
