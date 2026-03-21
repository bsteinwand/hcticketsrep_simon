package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record UserApplicationRoleUpdateDto(
        String status,
        String activeDate,
        String inactiveDate
) {}