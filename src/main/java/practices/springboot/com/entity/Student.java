package practices.springboot.com.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name ="student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;
}
