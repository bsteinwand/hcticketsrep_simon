package edu.helenacollege.hctickets.service.impl;

import java.util.List;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleUpdateDto;
import edu.helenacollege.hctickets.mapper.UserApplicationRoleMapper;
import edu.helenacollege.hctickets.model.Application;
import edu.helenacollege.hctickets.model.User;
import edu.helenacollege.hctickets.model.UserApplicationRole;
import edu.helenacollege.hctickets.repository.ApplicationRepository;
import edu.helenacollege.hctickets.repository.ApplicationRoleRepository;
import edu.helenacollege.hctickets.repository.UserApplicationRoleRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.UserApplicationRoleService;
import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
@Transactional
public class UserApplicationRoleServiceImpl implements UserApplicationRoleService {

    private final UserApplicationRoleRepository repository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationRoleRepository applicationRoleRepository;
    private final UserApplicationRoleMapper mapper;
    
    

    public UserApplicationRoleServiceImpl(UserApplicationRoleRepository repository, UserRepository userRepository,
			ApplicationRepository applicationRepository, ApplicationRoleRepository applicationRoleRepository,
			UserApplicationRoleMapper mapper) {
		super();
		this.repository = repository;
		this.userRepository = userRepository;
		this.applicationRepository = applicationRepository;
		this.applicationRoleRepository = applicationRoleRepository;
		this.mapper = mapper;
	}

	@Override
    public UserApplicationRoleResponseDto create(UserApplicationRoleCreateDto dto) {

        UserApplicationRole entity = new UserApplicationRole();

        User user = userRepository.findById(dto.userId())
        		.orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        if (!"Active".equalsIgnoreCase(user.getStatus())) {
        	throw new IllegalStateException("User is not active");
        }
        
        entity.setUser(user);
        
        Application application = applicationRepository.findById(dto.appId())
        		.orElseThrow(() -> new EntityNotFoundException("Application not found"));
        
        if (!"Active".equalsIgnoreCase(application.getStatus())) {
        	throw new IllegalStateException("Application is not active");
        }
        
        entity.setApplication(application);
        
        entity.setApplicationRole(
        		applicationRoleRepository.findById(dto.appRoleId())
        			.orElseThrow(() -> new EntityNotFoundException("ApplicationRole not found"))
        );
        
        if (!"Active".equalsIgnoreCase(dto.status())) {
            throw new IllegalStateException("New user application role assignments must have Active status");
        }

        entity.setStatus(dto.status());
        entity.setActiveDate(parseOffsetDateTime(dto.activeDate()));
        entity.setInactiveDate(parseOffsetDateTime(dto.inactiveDate()));

        return mapper.toResponseDto(repository.save(entity));
    }

    private OffsetDateTime parseOffsetDateTime(String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		return LocalDateTime.parse(value).atZone(ZoneId.systemDefault()).toOffsetDateTime();
	}

	@Override
    public UserApplicationRoleResponseDto update(Integer id, UserApplicationRoleUpdateDto dto) {
        UserApplicationRole entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));
        
        entity.setStatus(dto.status());
        entity.setActiveDate(parseOffsetDateTime(dto.activeDate()));
        entity.setInactiveDate(parseOffsetDateTime(dto.inactiveDate()));
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public UserApplicationRoleResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserApplicationRoleResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserApplicationRoleResponseDto> findActiveAssignmentsByUserId(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!"Active".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        return repository.findByUserIdAndStatus(userId, "Active").stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public UserApplicationRoleResponseDto unassign(Integer userApplicationRoleId) {

        UserApplicationRole entity = repository.findById(userApplicationRoleId)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));

        if (!"Active".equalsIgnoreCase(entity.getStatus())) {
            throw new IllegalStateException("Selected role assignment is already inactive");
        }

        if (!"Active".equalsIgnoreCase(entity.getUser().getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        if (!"Active".equalsIgnoreCase(entity.getApplication().getStatus())) {
            throw new IllegalStateException("Application is not active");
        }

        entity.setStatus("Inactive");
        entity.setInactiveDate(OffsetDateTime.now());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
