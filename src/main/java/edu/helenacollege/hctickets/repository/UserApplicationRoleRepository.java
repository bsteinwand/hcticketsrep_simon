package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import edu.helenacollege.hctickets.model.UserApplicationRole;

public interface UserApplicationRoleRepository extends JpaRepository<UserApplicationRole, Integer> {

    // Returns assignments for a user filtered by status (used for active role selection)
    List<UserApplicationRole> findByUserIdAndStatus(Integer userId, String status);
}