package edu.helenacollege.hctickets.dto;

public record UserApplicationRoleCreateDto(
        Integer appRoleId,
        Integer userId,
        Integer appId,
        String status
) {}
