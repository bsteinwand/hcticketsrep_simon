package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.RoleCreateDto;
import edu.helenacollege.hctickets.dto.RoleResponseDto;
import edu.helenacollege.hctickets.dto.RoleUpdateDto;
import edu.helenacollege.hctickets.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleCreateDto dto);

    RoleResponseDto toResponseDto(Role entity);

    void updateEntity(RoleUpdateDto dto, @MappingTarget Role entity);
}
