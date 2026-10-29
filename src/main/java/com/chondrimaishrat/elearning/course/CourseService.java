package com.chondrimaishrat.elearning.course;

import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> publishedCourses() {
        return courseRepository.findByStatusOrderByCreatedAtDesc(CourseStatus.PUBLISHED);
    }

    public List<Course> instructorCourses(UserAccount instructor) {
        return courseRepository.findByInstructorIdOrderByCreatedAtDesc(instructor.getId());
    }

    @Transactional
    public Course create(UserAccount instructor, CourseForm form) {
        if (instructor.getRole() != UserRole.INSTRUCTOR) {
            throw new IllegalArgumentException("Only instructors can create courses.");
        }

        return courseRepository.save(new Course(
                form.getTitle().trim(),
                form.getCategory().trim(),
                form.getDescription().trim(),
                form.getPrice(),
                instructor
        ));
    }

    @Transactional
    public void publish(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course was not found."));
        course.publish();
    }
}
