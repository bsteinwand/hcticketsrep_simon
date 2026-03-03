package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.TaskCreateDto;
import edu.helenacollege.hctickets.dto.TaskResponseDto;
import edu.helenacollege.hctickets.dto.TaskUpdateDto;
import edu.helenacollege.hctickets.mapper.TaskMapper;
import edu.helenacollege.hctickets.model.Task;
import edu.helenacollege.hctickets.repository.TaskRepository;
import edu.helenacollege.hctickets.repository.TicketRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.TaskService;
//import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

@Service
//@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;
    
    

    public TaskServiceImpl(TaskRepository repository, TicketRepository ticketRepository, UserRepository userRepository,
			TaskMapper mapper) {
		super();
		this.repository = repository;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
    public TaskResponseDto create(TaskCreateDto dto) {
        Task entity = new Task();

        entity.setName(dto.name());
        entity.setDetails(dto.details());
        entity.setStatus(dto.status());
        entity.setCreationDate(OffsetDateTime.now());

        entity.setTicket(
                ticketRepository.findById(dto.ticketId())
                        .orElseThrow(() -> new EntityNotFoundException("Ticket not found"))
        );

        entity.setUser(
                userRepository.findById(dto.userId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public TaskResponseDto update(Integer id, TaskUpdateDto dto) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        mapper.updateEntity(dto, entity);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}