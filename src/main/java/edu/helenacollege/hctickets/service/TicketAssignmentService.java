package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TicketAssignmentCreateDto;
import edu.helenacollege.hctickets.dto.TicketAssignmentResponseDto;
import edu.helenacollege.hctickets.dto.TicketAssignmentUpdateDto;

public interface TicketAssignmentService {

    TicketAssignmentResponseDto create(TicketAssignmentCreateDto dto);

    TicketAssignmentResponseDto update(Integer id, TicketAssignmentUpdateDto dto);

    TicketAssignmentResponseDto findById(Integer id);

    List<TicketAssignmentResponseDto> findAll();

    void delete(Integer id);
}
