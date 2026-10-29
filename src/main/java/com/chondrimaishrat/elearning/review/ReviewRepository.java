package com.chondrimaishrat.elearning.review;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ReviewRepository extends JpaRepository<Review,Long>{ List<Review> findByCourseIdOrderByIdDesc(Long id); boolean existsByStudentIdAndCourseId(Long studentId,Long courseId); }
