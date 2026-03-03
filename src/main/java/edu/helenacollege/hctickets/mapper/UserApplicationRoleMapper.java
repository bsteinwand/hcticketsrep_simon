package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleUpdateDto;
import edu.helenacollege.hctickets.model.UserApplicationRole;

@Mapper(componentModel = "spring")
public interface UserApplicationRoleMapper {

    UserApplicationRoleResponseDto toResponseDto(UserApplicationRole entity);

    void updateEntity(UserApplicationRoleUpdateDto dto, @MappingTarget UserApplicationRole entity);
}

