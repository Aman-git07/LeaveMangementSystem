package leaveManagement.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Employee Name can not be blank")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Employee Email can not be blank")
    @Email(message = "Invalid Email Format")

    private String email;

    @NotBlank(message = "Employee Password can not be blank")
    @Size(min = 6,message = "Password must be at least 6 characters long")
    private String password;
    private String role;

    public Employee() {
    }

    public Employee(String password, String role, String email, String name, Long id) {
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}