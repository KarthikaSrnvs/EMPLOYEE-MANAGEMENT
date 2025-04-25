package login.page.controller;

import login.page.model.Employee;
import login.page.model.User1;
import login.page.repository.EmployeeRepository;
import login.page.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository; 

    @GetMapping("/profile/{employeeId}")
    public ResponseEntity<Employee> getProfile(@PathVariable int employeeId) {
        return employeeRepository.findById(employeeId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile/{employeeId}")
    public ResponseEntity<Employee> updateProfile(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee existing = optionalEmployee.get();
            existing.setFirst_name(updatedEmployee.getFirst_name());
            existing.setLast_name(updatedEmployee.getLast_name());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setPhone_number(updatedEmployee.getPhone_number());
            existing.setPosition(updatedEmployee.getPosition());
            existing.setDepartment(updatedEmployee.getDepartment());
            existing.setDate_of_birth(updatedEmployee.getDate_of_birth());
            existing.setJoining_date(updatedEmployee.getJoining_date());

            employeeRepository.save(existing);
            return ResponseEntity.ok(existing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

@GetMapping("/employees/{employeeId}")
public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
    return employeeRepository.findById(employeeId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

@PutMapping("/employees/{employeeId}")
public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

    if (optionalEmployee.isPresent()) {
        Employee existing = optionalEmployee.get();
        
        // Update employee fields if provided in request
        if (updatedEmployee.getFirst_name() != null) {
            existing.setFirst_name(updatedEmployee.getFirst_name());
        }
        if (updatedEmployee.getLast_name() != null) {
            existing.setLast_name(updatedEmployee.getLast_name());
        }
        if (updatedEmployee.getEmail() != null) {
            existing.setEmail(updatedEmployee.getEmail());
        }
        if (updatedEmployee.getPhone_number() != null) {
            existing.setPhone_number(updatedEmployee.getPhone_number());
        }
        if (updatedEmployee.getPosition() != null) {
            existing.setPosition(updatedEmployee.getPosition());
        }
        if (updatedEmployee.getDepartment() != null) {
            existing.setDepartment(updatedEmployee.getDepartment());
        }
        if (updatedEmployee.getDate_of_birth() != null) {
            existing.setDate_of_birth(updatedEmployee.getDate_of_birth());
        }
        if (updatedEmployee.getJoining_date() != null) {
            existing.setJoining_date(updatedEmployee.getJoining_date());
        }

        Employee savedEmployee = employeeRepository.save(existing);
        return ResponseEntity.ok(savedEmployee);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@RequestBody Map<String, Object> payload) {
        try {
           
            Employee emp = new Employee();
            emp.setFirst_name((String) payload.get("first_name"));
            emp.setLast_name((String) payload.get("last_name"));
            emp.setEmail((String) payload.get("email"));
            emp.setPhone_number((String) payload.get("phone_number"));
            emp.setPosition((String) payload.get("position"));
            emp.setDate_of_birth((String) payload.get("date_of_birth"));
            emp.setDepartment((String) payload.get("department"));
            emp.setJoining_date((String) payload.get("joining_date"));

            
            Employee savedEmployee = employeeRepository.save(emp);

            
            User1 user = new User1();
            user.setEmployee_id(savedEmployee.getEmployee_id());
            user.setUsername((String) payload.get("username"));
            user.setPassword((String) payload.get("password"));

            userRepository.save(user);

            return ResponseEntity.ok("Employee and User1 created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating employee/user: " + e.getMessage());
        }
    }
    @DeleteMapping("/employees/{employeeId}")
@Transactional
public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
    try {
        if (!employeeRepository.existsById(employeeId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Employee not found.\"}");
        }

       
        System.out.println("Deleting user with employeeId: " + employeeId);
        userRepository.deleteByEmployeeId(employeeId);

        System.out.println("Deleting employee with employeeId: " + employeeId);
        employeeRepository.deleteById(employeeId);

        return ResponseEntity.ok("{\"message\": \"Employee and corresponding user deleted successfully.\"}");
    } catch (Exception e) {
        e.printStackTrace();
        
        System.err.println("Error occurred while deleting employee: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\": \"Error deleting employee/user: " + e.getMessage() + "\"}");
    }
}



}
