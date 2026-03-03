package edu.helenacollege.hctickets.dto;

public record UserCreateDto(
        String firstName,
        String lastName,
        String email,
        String smsNumber,
        String status,
        String salt,
        String passwordHash,
        String notificationPreference,
        Integer roleId
) {
	public UserCreateDto() {
		this("", "", "", "", "", "", "", "", 0);
	}
}

