package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.UserApplicationRole;
public interface UserApplicationRoleRepository extends JpaRepository<UserApplicationRole, Integer> {
}