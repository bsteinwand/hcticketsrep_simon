package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TicketCommentCreateDto;
import edu.helenacollege.hctickets.dto.TicketCommentResponseDto;
import edu.helenacollege.hctickets.dto.TicketCommentUpdateDto;

public interface TicketCommentService {

    TicketCommentResponseDto create(TicketCommentCreateDto dto);

    TicketCommentResponseDto update(Integer id, TicketCommentUpdateDto dto);

    TicketCommentResponseDto findById(Integer id);

    List<TicketCommentResponseDto> findAll();

    void delete(Integer id);
}