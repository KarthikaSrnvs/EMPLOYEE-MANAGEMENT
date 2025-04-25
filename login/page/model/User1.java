package login.page.model;

import jakarta.persistence.*;
@Entity
@Table(name = "user1")
public class User1 {
    
    @Id
    private int employee_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public int getEmployee_id() { return employee_id; }
    public void setEmployee_id(int employee_id) { this.employee_id = employee_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
