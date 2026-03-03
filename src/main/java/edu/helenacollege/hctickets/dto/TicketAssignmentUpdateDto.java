package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TicketAssignmentUpdateDto(
        OffsetDateTime inactiveDate
) {}
