package edu.helenacollege.hctickets.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleResponseDto;
import edu.helenacollege.hctickets.dto.UserApplicationRoleUpdateDto;
import edu.helenacollege.hctickets.mapper.UserApplicationRoleMapper;
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

        entity.setUser(
                userRepository.findById(dto.userId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );

        entity.setApplication(
                applicationRepository.findById(dto.appId())
                        .orElseThrow(() -> new EntityNotFoundException("Application not found"))
        );

        entity.setApplicationRole(
                applicationRoleRepository.findById(dto.appRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("ApplicationRole not found"))
        );

        entity.setStatus(dto.status());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public UserApplicationRoleResponseDto update(Integer id, UserApplicationRoleUpdateDto dto) {
        UserApplicationRole entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserApplicationRole not found"));

        mapper.updateEntity(dto, entity);
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
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
