package edu.helenacollege.hctickets.dto;

public record TicketUpdateDto(
        String title,
        String description,
        String status,
        String visibility,
        Integer closedBy
) {}
