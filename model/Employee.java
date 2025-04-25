package login.page.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String position;
    private String date_of_birth;
    private String department;
    private String joining_date;


    public int getEmployee_id() { return employee_id; }
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getEmail() { return email; }
    public String getPhone_number() { return phone_number; }
    public String getPosition() { return position; }
    public String getDate_of_birth() { return date_of_birth;}
    public String getDepartment() { return department; }
    public String getJoining_date() { return joining_date;}
    

    public void setEmployee_id(int employee_id) { this.employee_id = employee_id; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public void setPosition(String position) { this.position = position; }
    public void setDate_of_birth(String date_of_birth) { this.date_of_birth = date_of_birth;}
    public void setDepartment(String department) { this.department = department; }
    public void setJoining_date(String joining_date) { this.joining_date = joining_date;}
}
