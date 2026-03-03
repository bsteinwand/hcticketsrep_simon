package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.model.Application;
import edu.helenacollege.hctickets.model.ApplicationRole;
import edu.helenacollege.hctickets.model.Role;
import edu.helenacollege.hctickets.model.Status;


public interface DataCacheService {
	List<Role> findRoles();
	List<ApplicationRole> findApplicationRoles();
	List<Status> findStatusesByStatusType(String statusType);
	List<Status> findActiveStatuses();
	Role findRoleById(int id);
	Role findRoleByRoleName(String roleName);
	ApplicationRole findApplicationRoleById(int id);
	ApplicationRole findApplicationRoleByRoleName(String roleName);
	Status findStatusById(int id);
	Status findStatusByCode(String code);
	List<Application> findApplications();
	List<Application> findApplicationsByStatus(String status); 
	Application findApplicationByAppName(String appName);
	Application findApplicationById(int id);
	
}
