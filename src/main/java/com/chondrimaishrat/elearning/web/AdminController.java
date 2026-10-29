package com.chondrimaishrat.elearning.web;

import com.chondrimaishrat.elearning.admin.AdminService;
import com.chondrimaishrat.elearning.auth.SessionAccess;
import com.chondrimaishrat.elearning.course.CourseService;
import com.chondrimaishrat.elearning.user.UserRole;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final SessionAccess access;
    private final AdminService adminService;
    private final CourseService courseService;

    public AdminController(SessionAccess access, AdminService adminService, CourseService courseService) {
        this.access = access;
        this.adminService = adminService;
        this.courseService = courseService;
    }

    @GetMapping("/admin")
    public String dashboard(HttpSession session, Model model) {
        try {
            access.requireRole(session, UserRole.ADMIN);
            model.addAttribute("pendingInstructors", adminService.pendingInstructors());
            model.addAttribute("draftCourses", adminService.draftCourses());
            return "admin-dashboard";
        } catch (IllegalArgumentException exception) {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/instructors/{id}/approve")
    public String approveInstructor(@PathVariable Long id, HttpSession session) {
        try {
            access.requireRole(session, UserRole.ADMIN);
            adminService.approveInstructor(id);
            return "redirect:/admin";
        } catch (IllegalArgumentException exception) {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/courses/{id}/publish")
    public String publishCourse(@PathVariable Long id, HttpSession session) {
        try {
            access.requireRole(session, UserRole.ADMIN);
            courseService.publish(id);
            return "redirect:/admin";
        } catch (IllegalArgumentException exception) {
            return "redirect:/login";
        }
    }
}
