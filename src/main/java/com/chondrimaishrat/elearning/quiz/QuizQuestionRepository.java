package com.chondrimaishrat.elearning.quiz;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long>{ List<QuizQuestion> findByCourseIdOrderByIdAsc(Long courseId); long countByCourseId(Long courseId); }
