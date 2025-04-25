package login.page.repository;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import login.page.model.User1;

public interface UserRepository extends JpaRepository<User1, Integer> {
    Optional<User1> findByUsername(String username);
    @Modifying
    @Query("DELETE FROM User1 u WHERE u.employee_id = :employeeId")
    void deleteByEmployeeId(int employeeId);


}
