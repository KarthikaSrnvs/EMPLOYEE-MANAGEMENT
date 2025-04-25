package login.page.service;

import login.page.model.User1;
import login.page.model.Employee;
import login.page.repository.UserRepository;
import login.page.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository user1Repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Map<String, Object> authenticateAndGetRole(String username, String password) {
        Optional<User1> userOpt = user1Repository.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            int empId = userOpt.get().getEmployee_id();
            Optional<Employee> empOpt = employeeRepository.findById(empId);

            if (empOpt.isPresent()) {
                String position = empOpt.get().getPosition();
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("role", position); // e.g. "Admin"
                response.put("employeeId", empId);
                return response;
            }
        }

        Map<String, Object> fail = new HashMap<>();
        fail.put("status", "fail");
        return fail;
    }
}
