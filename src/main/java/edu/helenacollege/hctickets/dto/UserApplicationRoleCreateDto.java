package edu.helenacollege.hctickets.dto;

// Used when creating a new user role assignment
public record UserApplicationRoleCreateDto(
        Integer appRoleId,
        Integer userId,
        Integer appId,
        String activeDate,
        String inactiveDate,
        String status
) {}