package edu.helenacollege.hctickets.service;

import java.util.List;

import edu.helenacollege.hctickets.dto.TicketCreateDto;
import edu.helenacollege.hctickets.dto.TicketResponseDto;
import edu.helenacollege.hctickets.dto.TicketUpdateDto;

public interface TicketService {

    TicketResponseDto create(TicketCreateDto dto);

    TicketResponseDto update(Integer id, TicketUpdateDto dto);

    TicketResponseDto findById(Integer id);

    List<TicketResponseDto> findAll();

    void delete(Integer id);
}