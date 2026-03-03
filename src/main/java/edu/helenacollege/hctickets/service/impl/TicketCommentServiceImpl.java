package edu.helenacollege.hctickets.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.helenacollege.hctickets.service.TicketCommentService;

//@Service
////@RequiredArgsConstructor
//@Transactional
//public class TicketCommentServiceImpl implements TicketCommentService {
//
//    private final TicketCommentRepository repository;
//    private final TicketRepository ticketRepository;
//    private final UserRepository userRepository;
//    private final TicketCommentMapper mapper;
//
//    @Override
//    public TicketCommentResponseDto create(TicketCommentCreateDto dto) {
//
//        TicketComment entity = new TicketComment();
//
//        entity.setTicket(
//                ticketRepository.findById(dto.ticketId())
//                        .orElseThrow(() -> new EntityNotFoundException("Ticket not found"))
//        );
//
//        if (dto.userId() != null) {
//            entity.setUser(
//                    userRepository.findById(dto.userId())
//                            .orElseThrow(() -> new EntityNotFoundException("User not found"))
//            );
//        }
//
//        entity.setCommentText(dto.commentText
//		
//        		
//     Not complete, finish if needed   		