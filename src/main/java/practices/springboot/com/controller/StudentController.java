package practices.springboot.com.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import practices.springboot.com.entity.Student;
import practices.springboot.com.services.EmailService;
import practices.springboot.com.services.StudentSerVice;

@Controller
public class StudentController {
    @Autowired
    StudentSerVice studentSerVice;

    @Autowired
    EmailService emailService;
    @Autowired
    private SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    @GetMapping("login")
    public String home() {
        return "login";
    }

    @PostMapping("login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        boolean ifSuccess = studentSerVice.isSuccess(username, password);
        ModelAndView modelAndView = new ModelAndView();

        if (ifSuccess) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);
            modelAndView.setViewName("redirect:/home-page");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return modelAndView;
    }

    @GetMapping("home-page")
    public String homePage(HttpServletRequest request, Model model) {
        String username = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        model.addAttribute("username", username);
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


    @GetMapping("logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @GetMapping("reset-fail-page")
    public String resetFailPage() {
        return "reset-fail-page";
    }

    @GetMapping("reset-success-page")
    public String resetSuccessPage(){
        return "reset-success-page";
    }


    @GetMapping("reset-password")
    public String showResetPassword() {
        return "reset-password";
    }

    @PostMapping("reset-password")
    public ModelAndView resetPassword(@RequestParam("Email") String Email,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        String email = Email.trim();
        System.out.println(email);
        String newPassword = studentSerVice.resetPassword(email);

        if (newPassword != null) {
            emailService.sendNewPassword(email, newPassword);
            modelAndView.setViewName("redirect:/reset-success-page");
        } else {
            modelAndView.setViewName("redirect:/reset-fail-password");
        }
        return modelAndView;
    }


}
