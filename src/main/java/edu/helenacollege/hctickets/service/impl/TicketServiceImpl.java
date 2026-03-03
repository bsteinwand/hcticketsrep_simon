package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.TicketCreateDto;
import edu.helenacollege.hctickets.dto.TicketResponseDto;
import edu.helenacollege.hctickets.dto.TicketUpdateDto;
import edu.helenacollege.hctickets.mapper.TicketMapper;
import edu.helenacollege.hctickets.model.Ticket;
import edu.helenacollege.hctickets.repository.ApplicationRepository;
import edu.helenacollege.hctickets.repository.TicketRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final TicketMapper mapper;
    
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository,
			ApplicationRepository applicationRepository, TicketMapper mapper) {
		super();
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.applicationRepository = applicationRepository;
		this.mapper = mapper;
	}

	@Override
    public TicketResponseDto create(TicketCreateDto dto) {

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setStatus(dto.status());
        ticket.setVisibility(dto.visibility());
        ticket.setCreationDate(OffsetDateTime.now());

        ticket.setCreatedBy(
                userRepository.findById(dto.createdBy())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );

        ticket.setApplication(
                applicationRepository.findById(dto.applicationId())
                        .orElseThrow(() -> new EntityNotFoundException("Application not found"))
        );

        ticket = ticketRepository.save(ticket);
        return mapper.toResponseDto(ticket);
    }

    @Override
    public TicketResponseDto update(Integer id, TicketUpdateDto dto) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setStatus(dto.status());
        ticket.setVisibility(dto.visibility());

        if (dto.closedBy() != null) {
            ticket.setClosedBy(
                    userRepository.findById(dto.closedBy())
                            .orElseThrow(() -> new EntityNotFoundException("User not found"))
            );
            ticket.setClosedDate(OffsetDateTime.now());
        }

        ticket = ticketRepository.save(ticket);
        return mapper.toResponseDto(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponseDto findById(Integer id) {
        return ticketRepository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponseDto> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }
}