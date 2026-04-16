package edu.helenacollege.hctickets.controller;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.service.DataCacheService;
import edu.helenacollege.hctickets.service.UserApplicationRoleService;
import edu.helenacollege.hctickets.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applicationrole")
public class UserApplicationRoleController {
	
	private final UserApplicationRoleService service;
	private final DataCacheService dataCacheService;
	private final UserService userService;
	
	public UserApplicationRoleController(UserApplicationRoleService service,
	                                     DataCacheService dataCacheService,
	                                     UserService userService) {
		this.service = service;
		this.dataCacheService = dataCacheService;
		this.userService = userService;
	}
	
	// Loads main page with all assignments and reference data
	@GetMapping
	public String list(Model model) {
		model.addAttribute("assignment", new UserApplicationRoleCreateDto(null, null, null, null, null, null));
		
		model.addAttribute("users", userService.findAll());
		model.addAttribute("applications", dataCacheService.findApplications());
		model.addAttribute("roles", dataCacheService.findApplicationRoles());
		model.addAttribute("statuses", dataCacheService.findStatusesByStatusType("UserApplicationRole"));
		model.addAttribute("assignments", service.findAll());
		
		return "userapplicationrole/list";
	}
	
	// Returns assign form fragment for HTMX load
	@GetMapping("/new")
	public String newForm(Model model) {
	    model.addAttribute("assignment", new UserApplicationRoleCreateDto(null, null, null, null, null, null));
	    model.addAttribute("users", userService.findAll());
	    model.addAttribute("applications", dataCacheService.findApplications());
	    model.addAttribute("statuses", dataCacheService.findStatusesByStatusType("UserApplicationRole"));

	    return "userapplicationrole/form :: form";
	}
	
	// Loads active assignments for selected user into unassign section (HTMX)
	@GetMapping("/unassign/{userId}")
	public String loadUnassignForm(@PathVariable Integer userId, Model model) {
	    model.addAttribute("activeAssignments", service.findActiveAssignmentsByUserId(userId));
	    model.addAttribute("selectedUserId", userId);
	    return "userapplicationrole/unassign-form :: unassignForm";
	}
	
	// Loads a user-specific page showing active role assignments
	@GetMapping("/user/{userId}")
	public String viewUserRoles(@PathVariable Integer userId, Model model) {
	    model.addAttribute("activeAssignments", service.findActiveAssignmentsByUserId(userId));
	    model.addAttribute("selectedUserId", userId);
	    return "userapplicationrole/user-view";
	}
	
	// Handles creation of a new user role assignment
	@PostMapping
	public String create(@ModelAttribute("assignment") UserApplicationRoleCreateDto dto,
	                     BindingResult bindingResult,
	                     Model model,
	                     @RequestHeader(value = "HX-Request", required = false) String hxRequest) {

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("assignment", dto);
	        model.addAttribute("users", userService.findAll());
	        model.addAttribute("applications", dataCacheService.findApplications());
	        model.addAttribute("roles", dataCacheService.findApplicationRoles());
	        model.addAttribute("statuses", dataCacheService.findStatusesByStatusType("UserApplicationRole"));
	        model.addAttribute("assignments", service.findAll());

	        // Return only table rows if request came from HTMX
	        if ("true".equals(hxRequest)) {
	            return "userapplicationrole/list :: rows";
	        }

	        return "userapplicationrole/list";
	    }

	    try {
	        service.create(dto);
	    } catch (IllegalStateException exception) {
	        model.addAttribute("errorMessage", exception.getMessage());
	        model.addAttribute("assignment", dto);
	        model.addAttribute("users", userService.findAll());
	        model.addAttribute("applications", dataCacheService.findApplications());
	        model.addAttribute("roles", dataCacheService.findApplicationRoles());
	        model.addAttribute("statuses", dataCacheService.findStatusesByStatusType("UserApplicationRole"));
	        model.addAttribute("assignments", service.findAll());

	        return "userapplicationrole/list";
	    }

	    model.addAttribute("assignments", service.findAll());

	    // HTMX request refreshes only table rows
	    if ("true".equals(hxRequest)) {
	        return "userapplicationrole/list :: rows";
	    }

	    return "redirect:/applicationrole";
	}
	
	// Handles unassigning (inactivating) a selected user role
	@PostMapping("/unassign")
	public String unassignRole(@RequestParam("userApplicationRoleId") Integer userApplicationRoleId,
	                           @RequestParam("selectedUserId") Integer selectedUserId,
	                           Model model) {
	    try {
	        service.unassign(userApplicationRoleId);
	        model.addAttribute("successMessage", "Role unassigned successfully");
	    } catch (IllegalStateException | jakarta.persistence.EntityNotFoundException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	    }

	    model.addAttribute("assignment", new UserApplicationRoleCreateDto(null, null, null, null, null, null));
	    model.addAttribute("users", userService.findAll());
	    model.addAttribute("applications", dataCacheService.findApplications());
	    model.addAttribute("roles", dataCacheService.findApplicationRoles());
	    model.addAttribute("statuses", dataCacheService.findStatusesByStatusType("UserApplicationRole"));
	    model.addAttribute("assignments", service.findAll());

	    // Re-populate unassign section after post so UI remains consistent
	    model.addAttribute("activeAssignments", service.findActiveAssignmentsByUserId(selectedUserId));
	    model.addAttribute("selectedUserId", selectedUserId);

	    return "userapplicationrole/list";
	}
}