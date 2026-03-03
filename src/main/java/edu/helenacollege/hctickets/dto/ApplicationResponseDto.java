package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record ApplicationResponseDto(
        Integer id,
        String appName,
        String description,
        OffsetDateTime creationDate,
        String status
) {}