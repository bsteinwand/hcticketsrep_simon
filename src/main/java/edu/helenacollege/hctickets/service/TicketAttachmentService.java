package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TicketAttachmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentUpdateDto;

public interface TicketAttachmentService {

    TicketAttachmentResponseDto create(TicketAttachmentCreateDto dto);

    TicketAttachmentResponseDto update(Integer id, TicketAttachmentUpdateDto dto);

    TicketAttachmentResponseDto findById(Integer id);

    List<TicketAttachmentResponseDto> findAll();

    void delete(Integer id);
}