package com.chondrimaishrat.elearning.lesson;

import com.chondrimaishrat.elearning.course.Course;
import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "course_id") private Course course;
    @Column(nullable = false, length = 150) private String title;
    @Column(nullable = false, length = 4000) private String content;
    @Column(name = "video_url", length = 500) private String videoUrl;
    @Column(name = "resource_url", length = 500) private String resourceUrl;
    @Column(nullable = false) private int position;
    protected Lesson() { }
    public Lesson(Course course, String title, String content, String videoUrl, String resourceUrl, int position) { this.course=course; this.title=title; this.content=content; this.videoUrl=videoUrl; this.resourceUrl=resourceUrl; this.position=position; }
    public Long getId(){return id;} public Course getCourse(){return course;} public String getTitle(){return title;} public String getContent(){return content;} public String getVideoUrl(){return videoUrl;} public String getResourceUrl(){return resourceUrl;} public int getPosition(){return position;}
}
