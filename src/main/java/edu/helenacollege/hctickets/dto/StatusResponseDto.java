package edu.helenacollege.hctickets.dto;

public record StatusResponseDto(
        Integer id,
        String code,
        String value,
        String statusType,
        Integer itemOrder,
        Boolean active
) {}


