package com.chondrimaishrat.elearning.lesson;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion,Long>{ boolean existsByStudentIdAndLessonId(Long studentId,Long lessonId); long countByStudentIdAndLessonCourseId(Long studentId,Long courseId); }
