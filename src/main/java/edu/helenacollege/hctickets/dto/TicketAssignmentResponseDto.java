package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TicketAssignmentResponseDto(
        Integer id,
        Integer ticketId,
        Integer technicianId,
        OffsetDateTime activeDate,
        OffsetDateTime inactiveDate,
        Integer assignedBy
) {}