package com.chondrimaishrat.elearning.web;
import com.chondrimaishrat.elearning.auth.SessionAccess;
import com.chondrimaishrat.elearning.lesson.*;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserRole;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller public class LessonController { private final LessonService lessons; private final SessionAccess access; public LessonController(LessonService l,SessionAccess a){lessons=l;access=a;} @GetMapping("/courses/{id}/lessons") public String view(@PathVariable Long id,HttpSession s,Model m){try{UserAccount u=access.requireUser(s);m.addAttribute("lessons",lessons.forCourse(id));m.addAttribute("courseId",id);m.addAttribute("progress",u.getRole()==UserRole.STUDENT?lessons.progress(u,id):0);m.addAttribute("currentUser",s.getAttribute("currentUser"));return "lessons";}catch(IllegalArgumentException e){return "redirect:/login";}} @GetMapping("/instructor/courses/{id}/lessons") public String manage(@PathVariable Long id,Model m,HttpSession s){try{access.requireRole(s,UserRole.INSTRUCTOR);m.addAttribute("courseId",id);m.addAttribute("lessons",lessons.forCourse(id));m.addAttribute("lessonForm",new LessonForm());return "lesson-manage";}catch(IllegalArgumentException e){return "redirect:/login";}} @PostMapping("/instructor/courses/{id}/lessons") public String add(@PathVariable Long id,@Valid LessonForm f,BindingResult b,HttpSession s,Model m){try{UserAccount u=access.requireRole(s,UserRole.INSTRUCTOR);if(b.hasErrors()){m.addAttribute("courseId",id);m.addAttribute("lessons",lessons.forCourse(id));return "lesson-manage";}lessons.add(u,id,f);return "redirect:/instructor/courses/"+id+"/lessons";}catch(IllegalArgumentException e){return "redirect:/login";}} @PostMapping("/lessons/{id}/complete") public String complete(@PathVariable Long id,@RequestParam Long courseId,HttpSession s){try{lessons.complete(access.requireRole(s,UserRole.STUDENT),id);return "redirect:/courses/"+courseId+"/lessons";}catch(IllegalArgumentException e){return "redirect:/courses";}} }
