package edu.helenacollege.hctickets.dto;

import java.time.OffsetDateTime;

public record TicketCommentResponseDto(
        Integer id,
        Integer ticketId,
        Integer userId,
        String commentText,
        Boolean internalComment,
        OffsetDateTime commentDate,
        Integer parentCommentId
) {}


