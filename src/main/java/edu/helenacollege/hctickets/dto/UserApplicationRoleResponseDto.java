package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record UserApplicationRoleResponseDto(
		Integer id,
        Integer userId,
        String userName,
        Integer appId,
        String appName,
        Integer appRoleId,
        String appRoleName,
        String status,
        OffsetDateTime activeDate,
        OffsetDateTime inactiveDate
) {}

