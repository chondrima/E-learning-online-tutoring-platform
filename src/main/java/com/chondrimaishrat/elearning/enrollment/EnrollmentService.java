package com.chondrimaishrat.elearning.enrollment;

import com.chondrimaishrat.elearning.course.Course;
import com.chondrimaishrat.elearning.course.CourseRepository;
import com.chondrimaishrat.elearning.course.CourseStatus;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void enroll(UserAccount student, Long courseId) {
        if (student.getRole() != UserRole.STUDENT) {
            throw new IllegalArgumentException("Only students can enroll in a course.");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course was not found."));

        if (course.getStatus() != CourseStatus.PUBLISHED) {
            throw new IllegalArgumentException("This course is not available for enrollment.");
        }

        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), courseId)) {
            throw new IllegalArgumentException("You are already enrolled in this course.");
        }

        enrollmentRepository.save(new Enrollment(student, course));
    }
}
