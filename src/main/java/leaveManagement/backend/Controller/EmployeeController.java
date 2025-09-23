package leaveManagement.backend.Controller;

import jakarta.validation.Valid;
import leaveManagement.backend.DTO.EmployeeUpdateDTO;
import leaveManagement.backend.Entity.Employee;
import leaveManagement.backend.Repository.EmployeeRepository;
import leaveManagement.backend.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);  //200 OK
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        Employee saved =employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); //201 Created
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateDTO dto) {

        Employee existing = employeeService.getEmployeeById(id);

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setRole(dto.getRole());

        // Only update password if provided
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        Employee updated = employeeService.updateEmployee(existing);
        return ResponseEntity.ok(updated); // 200 OK
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); //204 No Content
    }
}
