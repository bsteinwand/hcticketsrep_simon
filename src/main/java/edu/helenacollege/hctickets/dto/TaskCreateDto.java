package edu.helenacollege.hctickets.dto;

public record TaskCreateDto(
        String name,
        String details,
        Integer ticketId,
        Integer userId,
        String status
) {}