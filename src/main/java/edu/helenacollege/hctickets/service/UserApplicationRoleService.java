package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleUpdateDto;

public interface UserApplicationRoleService {

    // Creates a new user role assignment
    UserApplicationRoleResponseDto create(UserApplicationRoleCreateDto dto);

    // Updates an existing assignment
    UserApplicationRoleResponseDto update(Integer id, UserApplicationRoleUpdateDto dto);

    // Retrieves a single assignment by ID
    UserApplicationRoleResponseDto findById(Integer id);

    // Retrieves all assignments for display
    List<UserApplicationRoleResponseDto> findAll();
    
    // Returns active assignments for a specific user (used by unassign UI)
    List<UserApplicationRoleResponseDto> findActiveAssignmentsByUserId(Integer userId);
    
    // Marks a user role assignment as inactive
    UserApplicationRoleResponseDto unassign(Integer userApplicationRoleId);

    // Deletes an assignment
    void delete(Integer id);
}