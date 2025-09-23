package leaveManagement.backend.Controller;

import leaveManagement.backend.Entity.Employee;
import leaveManagement.backend.Repository.EmployeeRepository;
import leaveManagement.backend.Security.JwtUtil;
import leaveManagement.backend.DTO.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail());

        if (employee == null || !passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }


        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(404).body(Map.of("error", "Invalid Password"));
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(employee.getEmail());
        return ResponseEntity.ok(Map.of("message", "Login Successful", "token", token));
    }
}
