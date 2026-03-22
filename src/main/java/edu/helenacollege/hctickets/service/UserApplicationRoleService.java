package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleUpdateDto;

public interface UserApplicationRoleService {

    UserApplicationRoleResponseDto create(UserApplicationRoleCreateDto dto);

    UserApplicationRoleResponseDto update(Integer id, UserApplicationRoleUpdateDto dto);

    UserApplicationRoleResponseDto findById(Integer id);

    List<UserApplicationRoleResponseDto> findAll();
    
    List<UserApplicationRoleResponseDto> findActiveAssignmentsByUserId(Integer userId);
    
    UserApplicationRoleResponseDto unassign(Integer userApplicationRoleId);

    void delete(Integer id);
}
