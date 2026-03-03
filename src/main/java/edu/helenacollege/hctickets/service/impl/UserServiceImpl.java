package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.UserCreateDto;
import edu.helenacollege.hctickets.dto.UserResponseDto;
import edu.helenacollege.hctickets.dto.UserUpdateDto;
import edu.helenacollege.hctickets.mapper.UserMapper;
import edu.helenacollege.hctickets.model.User;
import edu.helenacollege.hctickets.repository.RoleRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.UserService;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    
    
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, UserMapper mapper) {
		super();
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.mapper = mapper;
	}

	@Override
    public UserResponseDto create(UserCreateDto dto) {
        User entity = mapper.toEntity(dto);

        entity.setCreationDate(OffsetDateTime.now());
        entity.setRole(
                roleRepository.findById(dto.roleId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found"))
        );

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public UserResponseDto update(Integer id, UserUpdateDto dto) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        System.err.println(entity.getStatus());
//        mapper.updateEntity(dto, entity);  // Mapper is removing status
//        System.err.println(entity.getStatus());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setRole(
                roleRepository.findById(dto.roleId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found"))
        );  //dto.roleId()

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    
    @Override
    @Transactional(readOnly = true)
    
    public UserResponseDto findUserWithRole(int id){
    	User user = repository.findUserWithRole(id);
    	UserResponseDto userRespDto = new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getSmsNumber(), user.getCreationDate(), user.getInactiveDate(), user.getStatus(), user.getNotificationPreference(), user.getRole().getId());
    	//UserResponseDto userRespDto = mapper.toResponseDto(user); // would not populate roleId in UserResponseDto record.
    	return userRespDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
