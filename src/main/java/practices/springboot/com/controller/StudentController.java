package practices.springboot.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import practices.springboot.com.services.StudentSerVice;

@Controller

public class StudentController {
    @Autowired
    StudentSerVice studentSerVice;

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @PostMapping("home")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        boolean ifSuccess = studentSerVice.isSuccess(username, password);
        ModelAndView modelAndView = new ModelAndView();

        if (ifSuccess) {
            modelAndView.setViewName("redirect:/home-page");
            modelAndView.addObject("message", "Đăng nhập thành công!");
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return modelAndView;
    }

    @GetMapping("home-page")
    public String homePage() {
        return "home-page";
    }
}
