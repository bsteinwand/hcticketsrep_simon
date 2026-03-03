package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.Role;
public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByRoleName(String roleName);
}