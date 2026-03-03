package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import edu.helenacollege.hctickets.dto.TicketAttachmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentUpdateDto;
import edu.helenacollege.hctickets.model.TicketAttachment;

@Mapper(componentModel = "spring")
public interface TicketAttachmentMapper {

    TicketAttachmentResponseDto toResponseDto(TicketAttachment entity);

    void updateEntity(TicketAttachmentUpdateDto dto, @MappingTarget TicketAttachment entity);
}
