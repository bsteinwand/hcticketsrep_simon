package edu.helenacollege.hctickets.dto;

public record TicketCommentUpdateDto(
        String commentText,
        Boolean internalComment
) {}