package edu.helenacollege.hctickets.dto;

public record TicketAttachmentCreateDto(
        Integer ticketId,
        Integer userId,
        String fileName,
        String filePath,
        String fileType,
        String fileHash,
        String title
) {}
