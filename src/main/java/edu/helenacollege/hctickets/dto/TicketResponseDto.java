package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TicketResponseDto(
        Integer id,
        String title,
        String description,
        String status,
        Integer createdBy,
        Integer applicationId,
        OffsetDateTime creationDate,
        String visibility,
        OffsetDateTime closedDate,
        Integer closedBy
) {}
