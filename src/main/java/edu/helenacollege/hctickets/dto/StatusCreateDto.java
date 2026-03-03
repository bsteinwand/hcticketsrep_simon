package edu.helenacollege.hctickets.dto;

public record StatusCreateDto(
        String code,
        String value,
        String statusType,
        Integer itemOrder,
        Boolean active
) {}
