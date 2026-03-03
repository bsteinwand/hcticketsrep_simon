package edu.helenacollege.hctickets.dto;

public record TicketAssignmentCreateDto(
        Integer ticketId,
        Integer technicianId,
        Integer assignedBy
) {}