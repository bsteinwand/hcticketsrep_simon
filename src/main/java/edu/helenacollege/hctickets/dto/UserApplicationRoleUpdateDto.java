package edu.helenacollege.hctickets.dto;

// Used for updating an existing assignment
public record UserApplicationRoleUpdateDto(
        String status,
        String activeDate,
        String inactiveDate
) {}