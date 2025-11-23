package practical.employee.register.service;

import org.springframework.web.multipart.MultipartFile;
import practical.employee.register.model.Employee;
import practical.employee.register.request.CreateEmployeeRequest;
import practical.employee.register.request.UpdateEmployeeRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface EmployeeService
{
    Employee createEmployee(CreateEmployeeRequest request);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(int id, UpdateEmployeeRequest request);
    void deleteEmployee(int id);
    void uploadProfilePicture(int id, MultipartFile file);
    byte[] downloadProfilePicture(int id) throws IOException;
    ByteArrayInputStream exportAllEmployeesToPdf() throws Exception;
}
