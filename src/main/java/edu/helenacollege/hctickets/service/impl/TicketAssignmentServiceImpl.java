package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.TicketAssignmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAssignmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAssignmentUpdateDto;
import edu.helenacollege.hctickets.mapper.TicketAssignmentMapper;
import edu.helenacollege.hctickets.model.TicketAssignment;
import edu.helenacollege.hctickets.repository.TicketAssignmentRepository;
import edu.helenacollege.hctickets.repository.TicketRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.TicketAssignmentService;
import jakarta.persistence.EntityNotFoundException;

@Service
//@RequiredArgsConstructor
@Transactional
public class TicketAssignmentServiceImpl implements TicketAssignmentService {

    private final TicketAssignmentRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketAssignmentMapper mapper;
    
    public TicketAssignmentServiceImpl(TicketAssignmentRepository repository, TicketRepository ticketRepository,
			UserRepository userRepository, TicketAssignmentMapper mapper) {
		super();
		this.repository = repository;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
    public TicketAssignmentResponseDto create(TicketAssignmentCreateDto dto) {

        TicketAssignment entity = new TicketAssignment();

        entity.setTicket(
                ticketRepository.findById(dto.ticketId())
                        .orElseThrow(() -> new EntityNotFoundException("Ticket not found"))
        );

        entity.setTechnician(
                userRepository.findById(dto.technicianId())
                        .orElseThrow(() -> new EntityNotFoundException("Technician not found"))
        );

        entity.setAssignedBy(
                userRepository.findById(dto.assignedBy())
                        .orElseThrow(() -> new EntityNotFoundException("AssignedBy user not found"))
        );

        entity.setActiveDate(OffsetDateTime.now());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public TicketAssignmentResponseDto update(Integer id, TicketAssignmentUpdateDto dto) {
        TicketAssignment entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TicketAssignment not found"));

        mapper.updateEntity(dto, entity);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public TicketAssignmentResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("TicketAssignment not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketAssignmentResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}