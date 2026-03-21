package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record UserApplicationRoleCreateDto(
        Integer appRoleId,
        Integer userId,
        Integer appId,
        String activeDate,
        String inactiveDate,
        String status
) {}
