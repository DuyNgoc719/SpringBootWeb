package practices.springboot.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practices.springboot.com.entity.Student;
import practices.springboot.com.repository.StudentRepository;

import java.security.SecureRandom;

@Service
public class StudentSerVice {

    @Autowired
    private StudentRepository studentRepository;

    public boolean isSuccess(String username, String password) {
        Student student =studentRepository.findByUsernameAndPassword(username,password);
        return student != null;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student finByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public String resetPassword(String email) {
        Student student = finByEmail(email);
        if (student != null) {
            String newPassword = generationPassword(8);
            student.setPassword(newPassword);
            studentRepository.save(student);
            return newPassword;
        } else {
            return null;
        }
    }

    private String generationPassword(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }


}
