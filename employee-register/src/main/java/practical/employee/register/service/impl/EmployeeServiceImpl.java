package practical.employee.register.service.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practical.employee.register.model.Employee;
import practical.employee.register.repository.EmployeeRepository;
import practical.employee.register.request.CreateEmployeeRequest;
import practical.employee.register.request.UpdateEmployeeRequest;
import practical.employee.register.service.EmployeeService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private final String UPLOAD_DIR = "practical/employee/register/bucket/profileImage";

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest request)
    {
        Employee employee = new Employee();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(int id, UpdateEmployeeRequest request)
    {
        Employee employee = getEmployeeById(id);
        employee.setName(request.getName());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id)
    {
        employeeRepository.deleteById(id);
    }

    @Override
    public void uploadProfilePicture(int id, MultipartFile file)
    {
        Employee employee = getEmployeeById(id);
        Path path = Paths.get(UPLOAD_DIR + employee.getId() + "_" + file.getOriginalFilename());
        try
        {
            Files.write(path, file.getBytes());
            employee.setProfilePicture(path.toString());
            employeeRepository.save(employee);
        } catch (IOException e) {}
    }

    @Override
    public byte[] downloadProfilePicture(int id) throws IOException {
        Employee employee = getEmployeeById(id);
        Path path = Paths.get(employee.getProfilePicture());
        return Files.readAllBytes(path);
    }

    @Override
    public ByteArrayInputStream exportAllEmployeesToPdf() throws Exception
    {
        List<Employee> employees = employeeRepository.findAll();

        JasperReport jasperReport = JasperCompileManager.compileReport("practical/employee/register/bucket/jasperReport");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private int calculateAgeInDays(LocalDate birthDate)
    {
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getDays();
    }
}
