package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.ApplicationRoleCreateDto;
import edu.helenacollege.hctickets.dto.ApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.ApplicationRoleUpdateDto;
import edu.helenacollege.hctickets.model.ApplicationRole;

@Mapper(componentModel = "spring")
public interface ApplicationRoleMapper {

    ApplicationRole toEntity(ApplicationRoleCreateDto dto);

    ApplicationRoleResponseDto toResponseDto(ApplicationRole entity);

    void updateEntity(ApplicationRoleUpdateDto dto, @MappingTarget ApplicationRole entity);
}