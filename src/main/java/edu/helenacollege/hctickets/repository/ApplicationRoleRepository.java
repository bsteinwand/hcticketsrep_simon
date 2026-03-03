package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.helenacollege.hctickets.model.ApplicationRole;
import edu.helenacollege.hctickets.model.Status;

public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Integer> {
	ApplicationRole findByRoleName(String roleName);
}