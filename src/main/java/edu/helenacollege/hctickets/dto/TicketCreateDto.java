package edu.helenacollege.hctickets.dto;

public record TicketCreateDto(
        String title,
        String description,
        String status,
        Integer createdBy,
        Integer applicationId,
        String visibility
) {}
