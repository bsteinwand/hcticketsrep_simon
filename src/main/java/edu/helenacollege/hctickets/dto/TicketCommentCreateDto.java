package edu.helenacollege.hctickets.dto;

public record TicketCommentCreateDto(
        Integer ticketId,
        Integer userId,
        String commentText,
        Boolean internalComment,
        Integer parentCommentId
) {}