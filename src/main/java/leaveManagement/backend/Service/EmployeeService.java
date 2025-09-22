package leaveManagement.backend.Service;

import jakarta.validation.Valid;
import leaveManagement.backend.Entity.Employee;
import leaveManagement.backend.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){return employeeRepository.findAll();}

    public Employee saveEmployee(@Valid Employee employee){return employeeRepository.save(employee);}

    public Employee updateEmployee(@Valid Employee employee){return employeeRepository.save(employee);}

    public void deleteEmployee(Long id){employeeRepository.deleteById(id);}
}
