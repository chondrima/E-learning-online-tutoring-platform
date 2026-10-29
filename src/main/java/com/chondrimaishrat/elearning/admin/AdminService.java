package com.chondrimaishrat.elearning.admin;

import com.chondrimaishrat.elearning.course.Course;
import com.chondrimaishrat.elearning.course.CourseRepository;
import com.chondrimaishrat.elearning.course.CourseStatus;
import com.chondrimaishrat.elearning.user.AccountStatus;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserAccountRepository;
import com.chondrimaishrat.elearning.user.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserAccountRepository users;
    private final CourseRepository courses;

    public AdminService(UserAccountRepository users, CourseRepository courses) {
        this.users = users;
        this.courses = courses;
    }

    public List<UserAccount> pendingInstructors() {
        return users.findByRoleAndStatus(UserRole.INSTRUCTOR, AccountStatus.PENDING);
    }

    public List<Course> draftCourses() {
        return courses.findByStatusOrderByCreatedAtDesc(CourseStatus.DRAFT);
    }

    @Transactional
    public void approveInstructor(Long userId) {
        UserAccount instructor = users.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Instructor was not found."));
        if (instructor.getRole() != UserRole.INSTRUCTOR) {
            throw new IllegalArgumentException("Only instructor accounts can be approved.");
        }
        instructor.setStatus(AccountStatus.ACTIVE);
    }
}
