package com.chondrimaishrat.elearning.web;

import com.chondrimaishrat.elearning.auth.SessionAccess;
import com.chondrimaishrat.elearning.course.CourseForm;
import com.chondrimaishrat.elearning.course.CourseService;
import com.chondrimaishrat.elearning.enrollment.EnrollmentService;
import com.chondrimaishrat.elearning.payment.PaymentService;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserRole;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {
    private final CourseService courses;
    private final EnrollmentService enrollments;
    private final SessionAccess access;
    private final PaymentService payments;

    public CourseController(CourseService courses, EnrollmentService enrollments, SessionAccess access, PaymentService payments) {
        this.courses = courses;
        this.enrollments = enrollments;
        this.access = access;
        this.payments = payments;
    }

    @GetMapping("/courses")
    public String catalog(Model model, HttpSession session) {
        model.addAttribute("courses", courses.publishedCourses());
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "course-catalog";
    }

    @GetMapping("/instructor/courses")
    public String instructorCourses(Model model, HttpSession session) {
        try {
            UserAccount instructor = access.requireRole(session, UserRole.INSTRUCTOR);
            model.addAttribute("courseForm", new CourseForm());
            model.addAttribute("courses", courses.instructorCourses(instructor));
            return "instructor-courses";
        } catch (IllegalArgumentException exception) {
            return "redirect:/login";
        }
    }

    @PostMapping("/instructor/courses")
    public String createCourse(@Valid CourseForm form, BindingResult errors, Model model, HttpSession session) {
        try {
            UserAccount instructor = access.requireRole(session, UserRole.INSTRUCTOR);
            if (errors.hasErrors()) {
                model.addAttribute("courses", courses.instructorCourses(instructor));
                return "instructor-courses";
            }
            courses.create(instructor, form);
            return "redirect:/instructor/courses";
        } catch (IllegalArgumentException exception) {
            return "redirect:/login";
        }
    }

    @PostMapping("/courses/enroll")
    public String enroll(@RequestParam Long courseId, HttpSession session, RedirectAttributes messages) {
        try {
            enrollments.enroll(access.requireRole(session, UserRole.STUDENT), courseId);
            messages.addFlashAttribute("message", "You are enrolled successfully.");
        } catch (IllegalArgumentException exception) {
            messages.addFlashAttribute("errorMessage", exception.getMessage());
        }
        return "redirect:/courses";
    }

    @PostMapping("/courses/pay")
    public String pay(@RequestParam Long courseId, HttpSession session, RedirectAttributes messages) {
        try {
            payments.payAndEnroll(access.requireRole(session, UserRole.STUDENT), courseId);
            messages.addFlashAttribute("message", "Simulated payment successful. You are enrolled.");
        } catch (IllegalArgumentException exception) {
            messages.addFlashAttribute("errorMessage", exception.getMessage());
        }
        return "redirect:/courses";
    }
}
