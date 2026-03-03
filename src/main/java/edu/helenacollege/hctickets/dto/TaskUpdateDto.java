package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TaskUpdateDto(
        String name,
        String details,
        String status,
        OffsetDateTime completedDate
) {}
