package edu.helenacollege.hctickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import edu.helenacollege.hctickets.service.TicketAttachmentService;
import edu.helenacollege.hctickets.service.UserService;

@Controller
@RequestMapping("/ticket")
public class TicketAttachmentController {

    private final TicketAttachmentService service;
    private final UserService userService;

    public TicketAttachmentController(TicketAttachmentService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    // Loads upload page for a specific ticket
    @GetMapping("/{ticketId}/attachment/add")
    public String showUploadPage(@PathVariable Integer ticketId, Model model) {
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("users", userService.findAll());
        return "ticket/attachment-form";
    }

    // Handles file upload
    @PostMapping("/{ticketId}/attachment/add")
    public String uploadFile(@PathVariable Integer ticketId,
                             @RequestParam("userId") Integer userId,
                             @RequestParam("title") String title,
                             @RequestParam("file") MultipartFile file,
                             Model model) {

        try {
            service.uploadAttachment(ticketId, userId, title, file);
            model.addAttribute("successMessage", "File uploaded successfully");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("ticketId", ticketId);
        model.addAttribute("users", userService.findAll());
        return "ticket/attachment-form";
    }
}