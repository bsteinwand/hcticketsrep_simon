package edu.helenacollege.hctickets.dto;

public record UserUpdateDto(
        String firstName,
        String lastName,
        String email,
        String smsNumber,
        String status,
        String notificationPreference,
        Integer roleId
) {}
