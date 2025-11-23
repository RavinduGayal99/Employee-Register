package practical.employee.register.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateEmployeeRequest
{
    private String name;
    private String email;
    private LocalDate birthDate;
}
