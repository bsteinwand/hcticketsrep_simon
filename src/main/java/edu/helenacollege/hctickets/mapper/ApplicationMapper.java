package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.ApplicationCreateDto;
import edu.helenacollege.hctickets.dto.ApplicationResponseDto;
import edu.helenacollege.hctickets.dto.ApplicationUpdateDto;
import edu.helenacollege.hctickets.model.Application;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    Application toEntity(ApplicationCreateDto dto);

    ApplicationResponseDto toResponseDto(Application entity);

    void updateEntity(ApplicationUpdateDto dto, @MappingTarget Application entity);
}
