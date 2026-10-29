package com.chondrimaishrat.elearning.lesson;
import com.chondrimaishrat.elearning.user.UserAccount;
import jakarta.persistence.*;
@Entity @Table(name="lesson_completions",uniqueConstraints=@UniqueConstraint(columnNames={"student_id","lesson_id"}))
public class LessonCompletion { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @ManyToOne(optional=false) @JoinColumn(name="student_id") private UserAccount student; @ManyToOne(optional=false) @JoinColumn(name="lesson_id") private Lesson lesson; protected LessonCompletion(){} public LessonCompletion(UserAccount s,Lesson l){student=s;lesson=l;} }
