package practices.springboot.com.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import practices.springboot.com.entity.Student;
import practices.springboot.com.services.StudentSerVice;

@Controller

public class StudentController {
    @Autowired
    StudentSerVice studentSerVice;

    @GetMapping("login")
    public String home() {
        return "login";
    }

    @PostMapping("login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        boolean ifSuccess = studentSerVice.isSuccess(username, password);
        ModelAndView modelAndView = new ModelAndView();

        if (ifSuccess) {
            modelAndView.setViewName("redirect:/home-page");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return modelAndView;
    }

    @GetMapping("home-page")
    public String homePage() {
        return "home-page";
    }

    @GetMapping("register")
    public String register() {return "register";}

    @PostMapping("register")
    public String saveStudent(@ModelAttribute Student student) {

        studentSerVice.saveStudent(student);
        return "redirect:/success-page";
    }

    @GetMapping("success-page")
    public String successPage() {
        return "success-page";
    }

}
