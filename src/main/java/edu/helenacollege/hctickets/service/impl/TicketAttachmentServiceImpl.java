package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.dto.TicketAttachmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentUpdateDto;
import edu.helenacollege.hctickets.mapper.TicketAttachmentMapper;
import edu.helenacollege.hctickets.model.TicketAttachment;
import edu.helenacollege.hctickets.repository.TicketAttachmentRepository;
import edu.helenacollege.hctickets.repository.TicketRepository;
import edu.helenacollege.hctickets.repository.UserRepository;
import edu.helenacollege.hctickets.service.TicketAttachmentService;
import jakarta.persistence.EntityNotFoundException;


@Service
//@RequiredArgsConstructor
@Transactional
public class TicketAttachmentServiceImpl implements TicketAttachmentService {

    private final TicketAttachmentRepository repository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketAttachmentMapper mapper;
    
    public TicketAttachmentServiceImpl(TicketAttachmentRepository repository, TicketRepository ticketRepository,
			UserRepository userRepository, TicketAttachmentMapper mapper) {
		super();
		this.repository = repository;
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
    public TicketAttachmentResponseDto create(TicketAttachmentCreateDto dto) {

        TicketAttachment entity = new TicketAttachment();

        entity.setTicket(
                ticketRepository.findById(dto.ticketId())
                        .orElseThrow(() -> new EntityNotFoundException("Ticket not found"))
        );

        entity.setUser(
                userRepository.findById(dto.userId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );

        entity.setFileName(dto.fileName());
        entity.setFilePath(dto.filePath());
        entity.setFileType(dto.fileType());
        entity.setFileHash(dto.fileHash());
        entity.setTitle(dto.title());
        entity.setGuid(UUID.randomUUID());
        entity.setDateUpdated(OffsetDateTime.now());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public TicketAttachmentResponseDto update(Integer id, TicketAttachmentUpdateDto dto) {
        TicketAttachment entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TicketAttachment not found"));

        mapper.updateEntity(dto, entity);
        entity.setDateUpdated(OffsetDateTime.now());

        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public TicketAttachmentResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("TicketAttachment not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketAttachmentResponseDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}