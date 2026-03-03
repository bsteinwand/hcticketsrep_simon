package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TicketAttachmentResponseDto(
        Integer id,
        Integer ticketId,
        Integer userId,
        String fileName,
        String filePath,
        String fileType,
        String fileHash,
        String title,
        UUID guid,
        OffsetDateTime dateUpdated
) {}
