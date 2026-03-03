package edu.helenacollege.hctickets.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import edu.helenacollege.hctickets.dto.UserCreateDto;
import edu.helenacollege.hctickets.dto.UserResponseDto;
import edu.helenacollege.hctickets.dto.UserUpdateDto;
import edu.helenacollege.hctickets.model.User;

public interface UserService {

    UserResponseDto create(UserCreateDto dto);

    UserResponseDto update(Integer id, UserUpdateDto dto);

    UserResponseDto findById(Integer id);

    List<UserResponseDto> findAll();

    void delete(Integer id);
    
    UserResponseDto findUserWithRole(int id);
}