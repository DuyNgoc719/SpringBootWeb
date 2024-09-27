package practices.springboot.com.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="resetpassbytoken")
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name ="student_id" )
    private Student student;

    @Column(nullable = false)
    private Date expiryDate;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, Student student) {
        this.token = token;
        this.student = student;
        this.expiryDate = calculateExpiryDate(60);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes)  {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public boolean isExpired() {
        return new Date().after(expiryDate);
    }
}
