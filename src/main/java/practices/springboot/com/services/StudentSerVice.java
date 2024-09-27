package practices.springboot.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practices.springboot.com.entity.Student;
import practices.springboot.com.repository.StudentRepository;

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
}
