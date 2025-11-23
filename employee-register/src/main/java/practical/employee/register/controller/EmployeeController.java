package practical.employee.register.controller;

import jakarta.annotation.security.RolesAllowed;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practical.employee.register.model.Employee;
import practical.employee.register.request.CreateEmployeeRequest;
import practical.employee.register.request.UpdateEmployeeRequest;
import practical.employee.register.response.EmployeeResponse;
import practical.employee.register.service.EmployeeService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    private final String Role_Admin = "admin";

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper)
    {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest request)
    {
        Employee employee = employeeService.createEmployee(request);
        return ResponseEntity.ok(convertToResponse(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable int id)
    {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(convertToResponse(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody UpdateEmployeeRequest request)
    {
        Employee employee = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(convertToResponse(employee));
    }

    @RolesAllowed(Role_Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id)
    {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable int id, @RequestParam MultipartFile file)
    {
        employeeService.uploadProfilePicture(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> downloadProfilePicture(@PathVariable int id) throws IOException {
        byte[] picture = employeeService.downloadProfilePicture(id);
        return ResponseEntity.ok(picture);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdf() throws Exception
    {
        ByteArrayInputStream byteArrayInputStream = employeeService.exportAllEmployeesToPdf();
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "inline; filename=employee_registration.pdf")
                .body(new InputStreamResource(byteArrayInputStream));
    }

    private EmployeeResponse convertToResponse(Employee employee)
    {
        return modelMapper.map(employee, EmployeeResponse.class);
    }
}
