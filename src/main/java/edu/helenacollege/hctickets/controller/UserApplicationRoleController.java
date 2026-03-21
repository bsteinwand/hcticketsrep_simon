package edu.helenacollege.hctickets.controller;

import edu.helenacollege.hctickets.dto.UserApplicationRoleCreateDto;
import edu.helenacollege.hctickets.service.DataCacheService;
import edu.helenacollege.hctickets.service.UserApplicationRoleService;
import edu.helenacollege.hctickets.service.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/userApplicationRole")
public class UserApplicationRoleController {
	
	private final UserApplicationRoleService service;
	private final DataCacheService dataCacheService;
	private final UserService userService;
	
	public UserApplicationRoleController(UserApplicationRoleService service, DataCacheService dataCacheService, UserService userService) {
		
		this.service = service;
		this.dataCacheService = dataCacheService;
		this.userService = userService;
	}
	
	@GetMapping
	public String list (Model model) {
		model.addAttribute("assignment", new UserApplicationRoleCreateDto(null, null, null, null, null, null));
		
		model.addAttribute("users", userService.findAll());
		model.addAttribute("applications", dataCacheService.findApplications());
		model.addAttribute("roles", dataCacheService.findApplicationRoles());
		model.addAttribute("statuses", dataCacheService.findActiveStatuses());
		model.addAttribute("assignments" , service.findAll());
		
		return "userapplicationrole/list";
	}
	
	@GetMapping("/new")
	public String newForm(Model model) {
	    model.addAttribute("assignment", new UserApplicationRoleCreateDto(null, null, null, null, null, null));
	    model.addAttribute("users", userService.findAll());
	    model.addAttribute("applications", dataCacheService.findApplications());
	    model.addAttribute("roles", dataCacheService.findApplicationRoles());
	    model.addAttribute("statuses", dataCacheService.findActiveStatuses());

	    return "userapplicationrole/form :: form";
	}
	
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
	        model.addAttribute("statuses", dataCacheService.findActiveStatuses());
	        model.addAttribute("assignments", service.findAll());

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
	        model.addAttribute("statuses", dataCacheService.findActiveStatuses());
	        model.addAttribute("assignments", service.findAll());

	        return "userapplicationrole/list";
	    }

	    model.addAttribute("assignments", service.findAll());

	    if ("true".equals(hxRequest)) {
	        return "userapplicationrole/list :: rows";
	    }

	    return "redirect:/userApplicationRole";
	}

}
