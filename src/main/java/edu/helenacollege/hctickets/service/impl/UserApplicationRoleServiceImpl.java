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

@Service
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
        this.repository = repository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.applicationRoleRepository = applicationRoleRepository;
        this.mapper = mapper;
    }

    // Creates a new user-application-role assignment
    @Override
    public UserApplicationRoleResponseDto create(UserApplicationRoleCreateDto dto) {

        UserApplicationRole entity = new UserApplicationRole();

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Business rule: user must be active to receive a role
        if (!"Active".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        entity.setUser(user);

        Application application = applicationRepository.findById(dto.appId())
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));

        // Business rule: application must be active
        if (!"Active".equalsIgnoreCase(application.getStatus())) {
            throw new IllegalStateException("Application is not active");
        }

        entity.setApplication(application);

        entity.setApplicationRole(
                applicationRoleRepository.findById(dto.appRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("ApplicationRole not found"))
        );

        // Business rule: new assignments must start as Active
        if (!"Active".equalsIgnoreCase(dto.status())) {
            throw new IllegalStateException("New user application role assignments must have Active status");
        }

        entity.setStatus(dto.status());
        entity.setActiveDate(parseOffsetDateTime(dto.activeDate()));
        entity.setInactiveDate(parseOffsetDateTime(dto.inactiveDate()));

        return mapper.toResponseDto(repository.save(entity));
    }

    // Converts datetime-local input from UI into OffsetDateTime for persistence
    private OffsetDateTime parseOffsetDateTime(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return LocalDateTime.parse(value)
                .atZone(ZoneId.systemDefault())
                .toOffsetDateTime();
    }

    // Updates an existing assignment (generic update)
    @Override
    public UserApplicationRoleResponseDto update(Integer id, UserApplicationRoleUpdateDto dto) {
        UserApplicationRole entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));

        entity.setStatus(dto.status());
        entity.setActiveDate(parseOffsetDateTime(dto.activeDate()));
        entity.setInactiveDate(parseOffsetDateTime(dto.inactiveDate()));

        return mapper.toResponseDto(repository.save(entity));
    }

    // Retrieves a single assignment by ID
    @Override
    @Transactional(readOnly = true)
    public UserApplicationRoleResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));
    }

    // Retrieves all assignments (used for main table display)
    @Override
    @Transactional(readOnly = true)
    public List<UserApplicationRoleResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    // Returns only active assignments for a selected user (used in unassign UI)
    @Override
    @Transactional(readOnly = true)
    public List<UserApplicationRoleResponseDto> findActiveAssignmentsByUserId(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Prevent loading roles for inactive users
        if (!"Active".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        return repository.findByUserIdAndStatus(userId, "Active").stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    // Marks an assignment as inactive
    @Override
    public UserApplicationRoleResponseDto unassign(Integer userApplicationRoleId) {

        UserApplicationRole entity = repository.findById(userApplicationRoleId)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));

        // Prevent double-unassign
        if (!"Active".equalsIgnoreCase(entity.getStatus())) {
            throw new IllegalStateException("Selected role assignment is already inactive");
        }

        // Business rule: user must still be active
        if (!"Active".equalsIgnoreCase(entity.getUser().getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        // Business rule: application must still be active
        if (!"Active".equalsIgnoreCase(entity.getApplication().getStatus())) {
            throw new IllegalStateException("Application is not active");
        }

        // Mark assignment as inactive and stamp current time
        entity.setStatus("Inactive");
        entity.setInactiveDate(OffsetDateTime.now());

        return mapper.toResponseDto(repository.save(entity));
    }

    // Deletes an assignment
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}