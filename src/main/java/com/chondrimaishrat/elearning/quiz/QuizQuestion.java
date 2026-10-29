package com.chondrimaishrat.elearning.quiz;

import com.chondrimaishrat.elearning.course.Course;
import jakarta.persistence.*;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "course_id") private Course course;
    @Column(nullable = false, length = 1000) private String questionText;
    @Column(nullable = false, length = 300) private String optionA;
    @Column(nullable = false, length = 300) private String optionB;
    @Column(nullable = false, length = 300) private String optionC;
    @Column(nullable = false, length = 300) private String optionD;
    @Column(nullable = false, length = 1) private String correctOption;
    protected QuizQuestion() { }
    public QuizQuestion(Course course, String questionText, String a, String b, String c, String d, String correct) { this.course=course; this.questionText=questionText; optionA=a; optionB=b; optionC=c; optionD=d; correctOption=correct; }
    public Long getId(){return id;} public String getQuestionText(){return questionText;} public String getOptionA(){return optionA;} public String getOptionB(){return optionB;} public String getOptionC(){return optionC;} public String getOptionD(){return optionD;} public String getCorrectOption(){return correctOption;}
}
