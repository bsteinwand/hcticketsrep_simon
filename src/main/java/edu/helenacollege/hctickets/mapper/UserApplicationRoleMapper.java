package edu.helenacollege.hctickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.model.UserApplicationRole;

@Mapper(componentModel = "spring")
public interface UserApplicationRoleMapper {
	
	@Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", expression = "java(entity.getUser().getFirstName() + \" \" + entity.getUser().getLastName())")
    @Mapping(target = "appId", source = "application.id")
    @Mapping(target = "appName", source = "application.appName")
    @Mapping(target = "appRoleId", source = "applicationRole.id")
    @Mapping(target = "appRoleName", source = "applicationRole.roleName")
    @Mapping(target = "activeDate", source = "activeDate")
    @Mapping(target = "inactiveDate", source = "inactiveDate")

    UserApplicationRoleResponseDto toResponseDto(UserApplicationRole entity);
}

