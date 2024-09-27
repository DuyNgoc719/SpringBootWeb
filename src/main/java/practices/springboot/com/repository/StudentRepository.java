package practices.springboot.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practices.springboot.com.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByUsernameAndPassword(String username, String password);
    Student findByEmail(String email);

}
