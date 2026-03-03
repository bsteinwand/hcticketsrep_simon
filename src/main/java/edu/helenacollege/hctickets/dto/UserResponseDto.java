package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record UserResponseDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String smsNumber,
        OffsetDateTime creationDate,
        OffsetDateTime inactiveDate,
        String status,
        String notificationPreference,
        Integer roleId
) {}
