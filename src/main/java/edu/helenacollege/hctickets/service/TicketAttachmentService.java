package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TicketAttachmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentUpdateDto;
import org.springframework.web.multipart.MultipartFile;

public interface TicketAttachmentService {

    TicketAttachmentResponseDto create(TicketAttachmentCreateDto dto);

    TicketAttachmentResponseDto update(Integer id, TicketAttachmentUpdateDto dto);

    TicketAttachmentResponseDto findById(Integer id);

    List<TicketAttachmentResponseDto> findAll();
    
    // Uploads a file for a ticket after validating ticket, user, and file rules
    TicketAttachmentResponseDto uploadAttachment(Integer ticketId, Integer userId, String title, MultipartFile file);

    void delete(Integer id);
}