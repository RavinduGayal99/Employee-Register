package practical.employee.register.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private Date birthday;

    private String profilePicture;

    private int currentAgeInDays;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
