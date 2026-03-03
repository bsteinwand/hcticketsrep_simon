package edu.helenacollege.hctickets.dto;

public record StatusUpdateDto(
        String code,
        String value,
        String statusType,
        Integer itemOrder,
        Boolean active
) {}