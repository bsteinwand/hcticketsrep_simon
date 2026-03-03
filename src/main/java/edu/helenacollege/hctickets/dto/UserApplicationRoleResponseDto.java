package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record UserApplicationRoleResponseDto(
        Integer id,
        Integer appRoleId,
        Integer userId,
        Integer appId,
        String status,
        OffsetDateTime inactiveDate
) {}

