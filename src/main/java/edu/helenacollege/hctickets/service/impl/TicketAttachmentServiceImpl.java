package edu.helenacollege.hctickets.service.impl;

import java.time.OffsetDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.DigestUtils;

import edu.helenacollege.hctickets.dto.TicketAttachmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAttachmentUpdateDto;
import edu.helenacollege.hctickets.mapper.TicketAttachmentMapper;
import edu.helenacollege.hctickets.model.Ticket;
import edu.helenacollege.hctickets.model.TicketAttachment;
import edu.helenacollege.hctickets.model.User;
import edu.helenacollege.hctickets.repository.TicketAttachmentRepository;
import edu.helenacollege.hctickets.repository.TicketRepository;
import edu.helenacollege.hctickets.repository.UserApplicationRoleRepository;
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
    private final UserApplicationRoleRepository userApplicationRoleRepository;
    
    public TicketAttachmentServiceImpl(
            TicketAttachmentRepository repository,
            TicketRepository ticketRepository,
            UserRepository userRepository,
            UserApplicationRoleRepository userApplicationRoleRepository,
            TicketAttachmentMapper mapper) {

        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.userApplicationRoleRepository = userApplicationRoleRepository;
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
    
    // Handles file upload with validation and persistence
    @Override
    public TicketAttachmentResponseDto uploadAttachment(
            Integer ticketId,
            Integer userId,
            String title,
            MultipartFile file) {

        // Ticket validation
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        if (!"Active".equalsIgnoreCase(ticket.getStatus())) {
            throw new IllegalStateException("Ticket is not active");
        }

        // User validation
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!"Active".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("User is not active");
        }

        // User belongs to application
        boolean hasAccess = userApplicationRoleRepository
                .existsByUserIdAndApplicationIdAndStatus(
                        userId,
                        ticket.getApplication().getId(),
                        "Active"
                );

        if (!hasAccess) {
            throw new IllegalStateException("User is not active for this application");
        }

        // File validation
        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalStateException("Invalid file name");
        }

        // Block special characters
        if (fileName.matches(".*[\\*:\\;<>\\\\/].*")) {
            throw new IllegalStateException("Invalid characters in file name");
        }

        // Extract extension
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        List<String> blocked = List.of("exe", "sh", "bat", "cmd", "php", "swf", "svg", "js");

        if (blocked.contains(extension)) {
            throw new IllegalStateException("File type not allowed");
        }

        try {
            // Save file to disk
            String uploadDir = "uploads/";
            String storedFileName = UUID.randomUUID() + "_" + fileName;

            Path path = Paths.get(uploadDir + storedFileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            // Generate hash
            String fileHash = DigestUtils.md5DigestAsHex(file.getBytes());

            // Create entity
            TicketAttachment entity = new TicketAttachment();

            entity.setTicket(ticket);
            entity.setUser(user);
            entity.setFileName(fileName);
            entity.setFilePath(path.toString());
            entity.setFileType(extension);
            entity.setFileHash(fileHash);
            entity.setTitle(title);
            entity.setGuid(UUID.randomUUID());
            entity.setDateUpdated(OffsetDateTime.now());

            return mapper.toResponseDto(repository.save(entity));

        } catch (Exception e) {
            throw new RuntimeException("Error saving file", e);
        }
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