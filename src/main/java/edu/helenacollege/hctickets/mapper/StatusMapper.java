package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.StatusCreateDto;
import edu.helenacollege.hctickets.dto.StatusResponseDto;
import edu.helenacollege.hctickets.dto.StatusUpdateDto;
import edu.helenacollege.hctickets.model.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    Status toEntity(StatusCreateDto dto);

    StatusResponseDto toResponseDto(Status entity);

    void updateEntity(StatusUpdateDto dto, @MappingTarget Status entity);
}