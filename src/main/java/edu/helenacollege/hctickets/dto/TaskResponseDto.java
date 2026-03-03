package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TaskResponseDto(
        Integer id,
        String name,
        String details,
        Integer ticketId,
        Integer userId,
        String status,
        OffsetDateTime creationDate,
        OffsetDateTime completedDate
) {}