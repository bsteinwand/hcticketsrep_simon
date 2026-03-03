package edu.helenacollege.hctickets.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.helenacollege.hctickets.dto.ApplicationResponseDto;
import edu.helenacollege.hctickets.dto.ApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.RoleResponseDto;
import edu.helenacollege.hctickets.dto.StatusResponseDto;
import edu.helenacollege.hctickets.model.Application;
import edu.helenacollege.hctickets.model.ApplicationRole;
import edu.helenacollege.hctickets.model.Role;
import edu.helenacollege.hctickets.model.Status;
import edu.helenacollege.hctickets.repository.ApplicationRepository;
import edu.helenacollege.hctickets.repository.ApplicationRoleRepository;
import edu.helenacollege.hctickets.repository.RoleRepository;
import edu.helenacollege.hctickets.repository.StatusRepository;
import edu.helenacollege.hctickets.service.DataCacheService;

@Service
public class DataCacheServiceImpl implements DataCacheService {
	private final RoleRepository roleRepository;
	private final StatusRepository statusRepository;
	private final ApplicationRoleRepository applicationRoleRepository;
	private final ApplicationRepository applicationRepository;
	
	public DataCacheServiceImpl(RoleRepository roleRepository, StatusRepository statusRepository,
			ApplicationRoleRepository applicationRoleRepository, ApplicationRepository applicationRepository) {
		super();
		this.roleRepository = roleRepository;
		this.statusRepository = statusRepository;
		this.applicationRoleRepository = applicationRoleRepository;
		this.applicationRepository = applicationRepository;
	}

	@Override
	@Cacheable("allRoles")
	public List<Role> findRoles() {
		
		return roleRepository.findAll();
	}

	@Override
	@Cacheable("allApplicationRoles")
	public List<ApplicationRole> findApplicationRoles() {
		return applicationRoleRepository.findAll();
	}

	@Override
	@Cacheable("statusesBystatusType")
	public List<Status> findStatusesByStatusType(String statusType) {
		return statusRepository.findByStatusType(statusType);
	}

	@Override
	@Cacheable("activeStatuses")
	public List<Status> findActiveStatuses() {
		return statusRepository.findByActive(true);
	}
	
	@Override
	@Cacheable("roleById")
	public Role findRoleById(int id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	@Cacheable("roleByRoleName")
	public Role findRoleByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
//		Role role = null;
//		for(Role next : roleRepository.findAll()) {
//			if(next.getRoleName().equals(roleName)) {
//				role = next;
//				break;
//			}
//		}
//		return role;
	}

	@Override
	@Cacheable("applicationRoleById")
	public ApplicationRole findApplicationRoleById(int id) {
		return applicationRoleRepository.findById(id).orElse(null);
	}

	@Override
	@Cacheable("applicationRoleByName")
	public Application findApplicationByAppName(String appName) {
		return applicationRepository.findByAppName(appName);
	}

	@Override
	@Cacheable("statusById")
	public Status findStatusById(int id) {
		return statusRepository.findById(id).orElse(null);
	}

	@Override
	@Cacheable("statusByCode")
	public Status findStatusByCode(String code) {
		return statusRepository.findByCode(code); //'.orElse(null);
	}

	@Override
	@Cacheable("allApplications")
	public List<Application> findApplications() {
		return applicationRepository.findAll();
	}

	@Override
	@Cacheable("activeApplications")
	public List<Application> findApplicationsByStatus(String status) {
		return applicationRepository.findByStatus(status);
	}
	
	@Override
	@Cacheable("applicationById")
	public Application findApplicationById(int id) {
		return applicationRepository.findById(id).orElse(null);
	}

	@Override
	@Cacheable("applicationRoleByName")
	public ApplicationRole findApplicationRoleByRoleName(String roleName) {
		return applicationRoleRepository.findByRoleName(roleName);
	}







}
