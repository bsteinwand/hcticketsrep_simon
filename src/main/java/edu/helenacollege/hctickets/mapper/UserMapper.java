package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import edu.helenacollege.hctickets.dto.UserCreateDto;
import edu.helenacollege.hctickets.dto.UserResponseDto;
import edu.helenacollege.hctickets.dto.UserUpdateDto;
import edu.helenacollege.hctickets.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateDto dto);

    UserResponseDto toResponseDto(User entity);

    void updateEntity(UserUpdateDto dto, @MappingTarget User entity);
}