package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.TaskResponseDto;
import edu.helenacollege.hctickets.dto.TaskUpdateDto;
import edu.helenacollege.hctickets.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toResponseDto(Task entity);

    void updateEntity(TaskUpdateDto dto, @MappingTarget Task entity);
}