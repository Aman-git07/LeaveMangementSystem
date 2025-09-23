package leaveManagement.backend.Security;

import leaveManagement.backend.Entity.Employee;
import leaveManagement.backend.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found with email: " + email);
        }

        String role = employee.getRole();
        if (role == null || role.isBlank()) {
            throw new UsernameNotFoundException("User does not have any role assigned");
        }
        role = role.trim().toUpperCase();

        return new User(employee.getEmail(),
                employee.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );

    }

}
